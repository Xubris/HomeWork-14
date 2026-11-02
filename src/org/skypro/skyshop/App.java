package org.skypro.skyshop;

import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.search.BestResultNotFound;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.Searchable;

public class App {
    public static void main(String[] args) {
        System.out.println("=== Проверка валидации данных ===");

        try {
            new SimpleProduct("   ", 50);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        try {
            new SimpleProduct("Хлеб", 0);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        try {
            new DiscountedProduct("Сыр", -100, 20);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        try {
            new DiscountedProduct("Сыр", 300, 150);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        SimpleProduct product1 = new SimpleProduct("Хлеб", 50);
        SimpleProduct product2 = new SimpleProduct("Молоко", 80);
        DiscountedProduct product3 = new DiscountedProduct("Сыр", 300, 20);
        FixPriceProduct product4 = new FixPriceProduct("Мясо");
        DiscountedProduct product5 = new DiscountedProduct("Рыба", 250, 10);

        Article article1 = new Article("Польза хлеба", "Хлеб содержит много углеводов.");
        Article article2 = new Article("Как выбрать сыр", "Сыр должен быть свежим.");
        Article article3 = new Article("Рыба в рационе", "Рыба богата омега-3. Рыба очень полезна.");

        ProductBasket basket = new ProductBasket();
        basket.addProduct(product1);
        basket.addProduct(product2);
        basket.addProduct(product3);
        basket.addProduct(product4);
        basket.addProduct(product5);
        basket.printBasket();

        SearchEngine engine = new SearchEngine(10);
        engine.add(product1);
        engine.add(product2);
        engine.add(product3);
        engine.add(product4);
        engine.add(product5);
        engine.add(article1);
        engine.add(article2);
        engine.add(article3);

        System.out.println("\n=== Поиск 'Рыба' ===");
        printResults(engine.search("Рыба"));

        System.out.println("\n=== Поиск 'нет такого' ===");
        Searchable[] notFound = engine.search("нет такого");
        if (notFound[0] == null) {
            System.out.println("Ничего не найдено.");
        } else {
            printResults(notFound);
        }

        System.out.println("\n=== Поиск самого подходящего ===");
        try {
            Searchable best = engine.findBestMatch("Рыба");
            System.out.println("Лучший результат: " + best.getStringRepresentation());
        } catch (BestResultNotFound e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        System.out.println("\n=== Поиск самого подходящего (не найдено) ===");
        try {
            Searchable best = engine.findBestMatch("Шоколад");
            System.out.println("Лучший результат: " + best.getStringRepresentation());
        } catch (BestResultNotFound e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static void printResults(Searchable[] results) {
        boolean hasResults = false;
        for (Searchable s : results) {
            if (s != null) {
                System.out.println(s.getStringRepresentation());
                hasResults = true;
            }
        }
        if (!hasResults) {
            System.out.println("Ничего не найдено.");
        }
    }
}