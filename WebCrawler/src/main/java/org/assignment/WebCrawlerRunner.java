package org.assignment;

import java.util.*;
import java.util.stream.Collectors;

/**
 * This class uses WebCrawler to search for a word in the list of Urls given
 */
public class WebCrawlerRunner {

    public static void main(String[] args) {
        boolean toContinue = true;
        Scanner sc = new Scanner(System.in);
        while (toContinue) {
            try {
                WebCrawler obj = new WebCrawler();
                System.out.println("Enter the comma separated Urls to be searched. For Ex : http://www.google.com,http://www.gmail.com");
                String str = sc.nextLine();
                Set<String> urlsToBeSearched = Arrays.stream(str.split(",")).collect(Collectors.toSet());
                if (urlsToBeSearched.stream().noneMatch(obj::isValidUrl)) {
                    System.out.println("None of the Urls are valid urls. Terminating...");
                } else {
                    System.out.println("Taking only the valid urls from the input...");
                    urlsToBeSearched = urlsToBeSearched.stream().filter(obj::isValidUrl).collect(Collectors.toSet());
                    System.out.println("Enter the text to be searched");
                    String searchText = sc.nextLine();
                    List<String> resultUrls = urlsToBeSearched.stream().filter(url -> obj.crawl(url) && obj.searchForWord(searchText)).toList();
                    if (resultUrls.size() == 0) {
                        System.out.println("No Urls have the searched text");
                    } else {
                        System.out.println("Text Found in below urls");
                        resultUrls.forEach(System.out::println);
                    }
                }
            } catch (Exception e) {
                System.out.println("Some error occurred.. " + e.getMessage());
            }
            System.out.println("Want to Continue ? Y/N");
            toContinue = sc.nextLine().equals("Y");
        }
    }
}
