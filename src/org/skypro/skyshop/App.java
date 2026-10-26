package org.skypro.skyshop;

import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.Searchable;

public class App {
    public static void main(String[] args) {
        // Товары
        SimpleProduct product1 = new SimpleProduct("Хлеб", 50);
        SimpleProduct product2 = new SimpleProduct("Молоко", 80);
        DiscountedProduct product3 = new DiscountedProduct("Сыр", 300, 20);
        FixPriceProduct product4 = new FixPriceProduct("Мясо");
        DiscountedProduct product5 = new DiscountedProduct("Рыба", 250, 10);

        // Корзина (старая проверка)
        ProductBasket basket = new ProductBasket();
        basket.addProduct(product1);
        basket.addProduct(product2);
        basket.addProduct(product3);
        basket.addProduct(product4);
        basket.addProduct(product5);
        basket.printBasket();

        // Статьи
        Article article1 = new Article("Польза хлеба", "Хлеб содержит много углеводов.");
        Article article2 = new Article("Как выбрать сыр", "Сыр должен быть свежим.");
        Article article3 = new Article("Рыба в рационе", "Рыба богата омега-3.");

        // Поисковый движок
        SearchEngine engine = new SearchEngine(10);
        engine.add(product1);
        engine.add(product2);
        engine.add(product3);
        engine.add(product4);
        engine.add(product5);
        engine.add(article1);
        engine.add(article2);
        engine.add(article3);

        // Тестирование поиска
        System.out.println("\n=== Поиск 'Хлеб' ===");
        printResults(engine.search("Хлеб"));

        System.out.println("\n=== Поиск 'сыр' ===");
        printResults(engine.search("сыр"));

        System.out.println("\n=== Поиск 'рыба' ===");
        printResults(engine.search("рыба"));

        System.out.println("\n=== Поиск 'нет такого' ===");
        printResults(engine.search("нет такого"));
    }

    // Вспомогательный метод для красивого вывода
    private static void printResults(Searchable[] results) {
        for (Searchable s : results) {
            if (s != null) {
                System.out.println(s.getStringRepresentation());
            }
        }
    }
}