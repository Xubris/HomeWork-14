package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductBasket {
    private final Map<String, List<Product>> products = new HashMap<>();

    public void addProduct(Product product) {
        products.computeIfAbsent(product.getName(), k -> new ArrayList<>()).add(product);
    }

    public int getTotalPrice() {
        int sum = 0;
        for (List<Product> productList : products.values()) {
            for (Product product : productList) {
                sum += product.getPrice();
            }
        }
        return sum;
    }

    public void printBasket() {
        if (products.isEmpty()) {
            System.out.println("в корзине пусто");
            return;
        }

        int specialCount = 0;
        for (List<Product> productList : products.values()) {
            for (Product product : productList) {
                System.out.println(product);
                if (product.isSpecial()) {
                    specialCount++;
                }
            }
        }
        System.out.println("Итого: " + getTotalPrice());
        System.out.println("Специальных товаров: " + specialCount);
    }

    public boolean containsProductByName(String name) {
        return products.containsKey(name);
    }

    public void clearBasket() {
        products.clear();
    }

    public List<Product> removeProductsByName(String name) {
        List<Product> removed = products.remove(name);
        if (removed == null) {
            return new ArrayList<>();
        }
        return removed;
    }
}