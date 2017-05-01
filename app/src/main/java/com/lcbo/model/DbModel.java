package com.lcbo.model;

import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;

import com.lcbo.model.db.Cart;
import com.lcbo.model.db.CartDao;
import com.lcbo.model.db.DaoMaster;
import com.lcbo.model.db.DaoSession;
import com.lcbo.model.db.Product;
import com.lcbo.model.db.ProductDao;
import com.lcbo.model.db.Store;
import com.lcbo.model.db.StoreDao;
import com.lcbo.model.db.StoreProductRelation;
import com.lcbo.model.db.StoreProductRelationDao;
import com.lcbo.model.pojo.Stores.Result;
import com.lcbo.model.pojo.Stores.Stores;
import com.lcbo.util.Util;

import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DbModel {

    private Database mDb;
    private DaoMaster mDaoMaster;

    public DbModel(Context context) {
        this.mDb = new DaoMaster.DevOpenHelper(context, "lcbo", null).getWritableDb();
        mDaoMaster = new DaoMaster(mDb);
    }

    public Observable<Boolean> addNewStores(Stores newStores) {
        return Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {

                ArrayList<Store> stores = new ArrayList<>();
                for(Result item: newStores.getResult()){
                    stores.add(new Store( (long) item.getId(), item.isIsDead(), item.getName(), item.getTags(),
                            item.getAddressLine1(), (String) item.getAddressLine2(), item.getCity(),
                            item.getPostalCode(), item.getTelephone(), item.getLatitude(),
                            item.getLongitude(), item.getProductsCount(), item.getInventoryCount(),
                            item.getInventoryPriceInCents(), item.getInventoryVolumeInMilliliters(),
                            item.isHasWheelchairAccessability(), item.isHasBilingualServices(),
                            item.isHasProductConsultant(), item.isHasTastingBar(), item.isHasBeerColdRoom(),
                            item.isHasSpecialOccasionPermits(), item.isHasVintagesCorner(),
                            item.isHasParking(), item.isHasTransitAccess(),
                            item.getUpdatedAt()));
                }
                DaoSession daoSession = mDaoMaster.newSession();
                StoreDao storeDao = daoSession.getStoreDao();
                try {
                    storeDao.insertOrReplaceInTx(stores);
                }catch (SQLiteConstraintException e){
                    e.printStackTrace();
                }
                subscriber.onNext(true);

            }}).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<ArrayList<Result>> getAllStoresByNameAndFilter(String cityName, String filter) {
        return Observable.create(new Observable.OnSubscribe<ArrayList<Result>>() {
            @Override
            public void call(Subscriber<? super ArrayList<Result>> subscriber) {

                DaoSession daoSession = mDaoMaster.newSession();
                StoreDao storeDao = daoSession.getStoreDao();

                QueryBuilder<Store> queryBuilder = storeDao.queryBuilder()
                        .where(StoreDao.Properties.City.eq(cityName));
                if(cityName.equals("")){
                    queryBuilder = storeDao.queryBuilder();
                }
                ArrayList<String> parsedFilter = Util.parseFilter(filter);
                for(String filter: parsedFilter){
                    switch(filter){
                        case "has_parking":
                            queryBuilder = queryBuilder.where(StoreDao.Properties.HasParking.eq(true));
                            break;
                        case "has_vintages_corner":
                            queryBuilder = queryBuilder.where(StoreDao.Properties.HasVintagesCorner.eq(true));
                            break;
                        case "has_special_occasion_permits":
                            queryBuilder = queryBuilder.where(StoreDao.Properties.HasSpecialOccasionPermits.eq(true));
                            break;
                        case "has_beer_cold_room":
                            queryBuilder = queryBuilder.where(StoreDao.Properties.HasBeerColdRoom.eq(true));
                            break;
                        case "has_tasting_bar":
                            queryBuilder = queryBuilder.where(StoreDao.Properties.HasTastingBar.eq(true));
                            break;
                        case "has_product_consultant":
                            queryBuilder = queryBuilder.where(StoreDao.Properties.HasProductConsultant.eq(true));
                            break;
                        case "has_bilingual_services":
                            queryBuilder = queryBuilder.where(StoreDao.Properties.HasBilingualServices.eq(true));
                            break;
                        case "has_wheelchair_accessability":
                            queryBuilder = queryBuilder.where(StoreDao.Properties.HasWheelchairAccessability.eq(true));
                            break;
                        case "has_transit_access":
                            queryBuilder = queryBuilder.where(StoreDao.Properties.HasTransitAccess.eq(true));
                            break;
                        default:
                            break;
                    }
                }

                List<Store> stores = queryBuilder.list();
                ArrayList<Result> result = formatStoresToResult(stores);
                subscriber.onNext(result);
            }}).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Cart> getProductFromCartById(com.lcbo.model.pojo.Products.Result product) {
        return Observable.create(new Observable.OnSubscribe<Cart>() {
            @Override
            public void call(Subscriber<? super Cart> subscriber) {

                DaoSession daoSession = mDaoMaster.newSession();
                CartDao daoCart = daoSession.getCartDao();
                List<Cart> cart = daoCart.queryBuilder().where(CartDao.Properties.ProductId.eq(product.getId())).list();
                if(cart.size() == 0){
                    subscriber.onNext(null);
                }else {
                    subscriber.onNext(cart.get(0));
                }

            }}).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Boolean> addOrUpdateProductToCart(com.lcbo.model.pojo.Products.Result product, int count) {
        return Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                getProductFromCartById(product).subscribe(cart -> {
                    DaoSession daoSession = mDaoMaster.newSession();
                    CartDao daoCart = daoSession.getCartDao();
                   if (cart == null){
                       daoCart.insert(new Cart(null, product.getId(), product.getName(), product.getPackage(), product.getPrimaryCategory(),
                               product.getSecondaryCategory(), product.getImageThumbUrl(), count, product.getPriceInCents()));
                   }else {
                       cart.setCount(cart.getCount() + count);
                       daoCart.update(cart);
                   }
                });
            }}).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<List<Cart>> getAllProductsFromCart() {
        return Observable.create(new Observable.OnSubscribe<List<Cart>>() {
            @Override
            public void call(Subscriber<? super List<Cart>> subscriber) {
                DaoSession daoSession = mDaoMaster.newSession();
                CartDao daoCart = daoSession.getCartDao();
                List<Cart> cartsList = daoCart.queryBuilder().list();

                subscriber.onNext(cartsList);
            }}).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Boolean> editProduct(com.lcbo.model.pojo.Products.Result product, int count) {
        return Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                getProductFromCartById(product).subscribe(cart -> {
                    DaoSession daoSession = mDaoMaster.newSession();
                    CartDao daoCart = daoSession.getCartDao();
                    if (cart == null){
                        daoCart.insert(new Cart(null, product.getId(), product.getName(), product.getPackage(), product.getPrimaryCategory(),
                                product.getSecondaryCategory(), product.getImageUrl(), count, product.getPriceInCents()));
                    }else {
                        cart.setCount(count);
                        daoCart.update(cart);
                    }
                    subscriber.onNext(true);
                });
            }}).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Boolean> addProductsToBd(com.lcbo.model.pojo.Products.StoreProducts productList) {
        return Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                try {
                    throw new Throwable();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
                ArrayList<Product> products = new ArrayList<Product>();
                for (com.lcbo.model.pojo.Products.Result item : productList.getResult()){
                    products.add(new Product((long) item.getId(), item.getName(), item.getPriceInCents(),
                            item.getPrimaryCategory(), item.getSecondaryCategory(), item.getPackage(),
                            item.getProducerName(), (String) item.getReleasedOn(), item.isHasValueAddedPromotion(),
                            item.isHasLimitedTimeOffer(), item.isHasBonusRewardMiles(), item.isIsSeasonal(),
                            item.isIsVqa(), item.isIsOcb(), item.isIsKosher(), (String) item.getValueAddedPromotionDescription(),
                            (String) item.getDescription(), item.getOrigin(),(String) item.getServingSuggestion(),
                            (String) item.getTastingNote(), item.getUpdatedAt(), item.getImageThumbUrl(), item.getImageUrl()));
                }

                DaoSession daoSession = mDaoMaster.newSession();
                ProductDao daoProduct = daoSession.getProductDao();
                daoProduct.insertOrReplaceInTx(products);
            }}).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Boolean> addProductsRelationAndProductsToDb(com.lcbo.model.pojo.Products.StoreProducts products, int storeId) {
        return Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                List<com.lcbo.model.pojo.Products.Result> productList = products.getResult();
                addProductsToBd(products).subscribe();
                ArrayList<StoreProductRelation> relations = new ArrayList<StoreProductRelation>();
                for(com.lcbo.model.pojo.Products.Result item: productList){
                    relations.add(new StoreProductRelation(null, (long) storeId, (long) item.getId(),
                            String.valueOf((Integer.toString(storeId)) + Long.toString(item.getId()))));
                }

                DaoSession daoSession = mDaoMaster.newSession();
                StoreProductRelationDao relationDao = daoSession.getStoreProductRelationDao();
                relationDao.insertOrReplaceInTx(relations);
            }}).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<List<com.lcbo.model.pojo.Products.Result>> getAllProductsByStore(int storeId) {
        return Observable.create(new Observable.OnSubscribe<List<com.lcbo.model.pojo.Products.Result>>() {
            @Override
            public void call(Subscriber<? super List<com.lcbo.model.pojo.Products.Result>> subscriber) {
                DaoSession daoSession = mDaoMaster.newSession();
                StoreDao storeDao = daoSession.getStoreDao();
                Store store = storeDao.queryBuilder().where(StoreDao.Properties.Id.eq(storeId)).unique();
                List<Product> products = store.getProductsInStore();
                List<com.lcbo.model.pojo.Products.Result> results = formatProductsToResult(products);

                subscriber.onNext(results);
            }}).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<List<com.lcbo.model.pojo.Products.Result>> getAllProductByNameAndFilter(
            String productName, String filter) {
        return Observable.create(new Observable.OnSubscribe<List<com.lcbo.model.pojo.Products.Result>>() {
            @Override
            public void call(Subscriber<? super List<com.lcbo.model.pojo.Products.Result>> subscriber) {
                DaoSession daoSession = mDaoMaster.newSession();
                ProductDao productDao = daoSession.getProductDao();
                QueryBuilder<Product> queryBuilder =  productDao.queryBuilder().where(ProductDao.Properties.Name.eq(productName));
                ArrayList<String> parsedFilter = Util.parseFilter(filter);
                for(String filter: parsedFilter){
                    switch(filter){
                        case "is_discontinued":
                            queryBuilder = queryBuilder.where(ProductDao.Properties.IsKosher.eq(true));
                            break;
                        case "has_value_added_promotion":
                            queryBuilder = queryBuilder.where(ProductDao.Properties.HasValueAddedPromotion.eq(true));
                            break;
                        case "has_limited_time_offer":
                            queryBuilder = queryBuilder.where(ProductDao.Properties.HasLimitedTimeOffer.eq(true));
                            break;
                        case "has_bonus_reward_miles":
                            queryBuilder = queryBuilder.where(ProductDao.Properties.HasBonusRewardMiles.eq(true));
                            break;
                        case "is_seasonal":
                            queryBuilder = queryBuilder.where(ProductDao.Properties.IsSeasonal.eq(true));
                            break;
                        case "is_vqa":
                            queryBuilder = queryBuilder.where(ProductDao.Properties.IsVqa.eq(true));
                            break;
                        case "is_ocb":
                            queryBuilder = queryBuilder.where(ProductDao.Properties.IsOcb.eq(true));
                            break;
                        case "is_kosher":
                            queryBuilder = queryBuilder.where(ProductDao.Properties.IsKosher.eq(true));
                            break;
                        default:
                            break;
                    }
                }

                List<Product> products = queryBuilder.list();
                ArrayList<com.lcbo.model.pojo.Products.Result> results = formatProductsToResult(products);

                subscriber.onNext(results);
            }}).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<List<com.lcbo.model.pojo.Products.Result>> getAllProductsByCategory(
            String productCategory) {
        return Observable.create(new Observable.OnSubscribe<List<com.lcbo.model.pojo.Products.Result>>() {
            @Override
            public void call(Subscriber<? super List<com.lcbo.model.pojo.Products.Result>> subscriber) {
                DaoSession daoSession = mDaoMaster.newSession();
                ProductDao productDao = daoSession.getProductDao();

                List<Product> products = productDao.queryBuilder().where(ProductDao.Properties.PrimaryCategory.eq(productCategory)).list();
                List<com.lcbo.model.pojo.Products.Result> results = formatProductsToResult(products);

                subscriber.onNext(results);
            }}).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Boolean> deleteAllInCart() {
        return Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                DaoSession daoSession = mDaoMaster.newSession();
                CartDao daoCart = daoSession.getCartDao();
                daoCart.deleteAll();
                subscriber.onNext(true);
            }}).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Boolean> deleteCartItemById(long cartId) {
        return Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                DaoSession daoSession = mDaoMaster.newSession();
                CartDao daoCart = daoSession.getCartDao();
                daoCart.deleteByKey(cartId);
                subscriber.onNext(true);
            }}).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
    }

    private ArrayList<Result> formatStoresToResult(List<Store> stores){
        ArrayList<com.lcbo.model.pojo.Stores.Result> results = new ArrayList<>();
        for (Store item: stores){
            Result newResult = new Result();
            newResult.setId((int )(long) item.getId());
            newResult.setAddressLine1(item.getAddressLine1());
            newResult.setAddressLine2(item.getAddressLine2());
            newResult.setName(item.getName());
            newResult.setCity(item.getCity());
            newResult.setTelephone(item.getTelephone());
            newResult.setLatitude(item.getLatitude());
            newResult.setLongitude(item.getLongitude());
            results.add(newResult);
        }

        return results;
    }

    private ArrayList<com.lcbo.model.pojo.Products.Result> formatProductsToResult(List<Product> products){
        ArrayList<com.lcbo.model.pojo.Products.Result> results = new ArrayList<com.lcbo.model.pojo.Products.Result>();
        for(Product item: products){
            com.lcbo.model.pojo.Products.Result result = new com.lcbo.model.pojo.Products.Result();
            result.setId((int) (long)item.getId());
            result.setName(item.getName());
            result.setPackage(item.get_package());
            result.setPriceInCents(item.getPriceInCents());
            result.setImageUrl(item.getImageThumbUrl());
            result.setImageThumbUrl(item.getImageThumbUrl());
            result.setReleasedOn(item.getReleasedOn());
            result.setProducerName(item.getProducerName());
            result.setOrigin(item.getOrigin());
            result.setDescription(item.getDescription());
            result.setPrimaryCategory(item.getPrimaryCategory());
            result.setSecondaryCategory(item.getSecondaryCategory());
            result.setTastingNote(item.getTastingNote());
            result.setServingSuggestion(item.getServingSuggestion());
            results.add(result);
        }

        return results;
    }
}
