package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ProductBasket {
    private final List<Product> products = new LinkedList<>();

    public void addProduct(Product product) {
        products.add(product);
    }

    public int getTotalPrice() {
        int sum = 0;
        for (Product product : products) {
            sum += product.getPrice();
        }
        return sum;
    }

    public void printBasket() {
        if (products.isEmpty()) {
            System.out.println("в корзине пусто");
            return;
        }

        int specialCount = 0;
        for (Product product : products) {
            System.out.println(product);
            if (product.isSpecial()) {
                specialCount++;
            }
        }
        System.out.println("Итого: " + getTotalPrice());
        System.out.println("Специальных товаров: " + specialCount);
    }

    public boolean containsProductByName(String name) {
        for (Product product : products) {
            if (product.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void clearBasket() {
        products.clear();
    }

    public List<Product> removeProductsByName(String name) {
        List<Product> removed = new ArrayList<>();
        Iterator<Product> iterator = products.iterator();

        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getName().equals(name)) {
                removed.add(product);
                iterator.remove();
            }
        }
        return removed;
    }
}