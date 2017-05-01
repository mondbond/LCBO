package com.lcbo.view;

import com.lcbo.model.db.Cart;
import java.util.List;

public interface CartView {
    void setProductsFromCarts(List<Cart> carts);
}
