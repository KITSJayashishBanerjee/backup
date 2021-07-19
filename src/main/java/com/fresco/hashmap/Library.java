package com.fresco.hashmap;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

class Library
{
    
    String bookName;
    String author;
    Library()
    {
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.bookName);
        hash = 83 * hash + Objects.hashCode(this.author);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Library other = (Library) obj;
        if (!Objects.equals(this.bookName, other.bookName)) {
            return false;
        }
        if (!Objects.equals(this.author, other.author)) {
            return false;
        }
        return true;
    }
    
    
    Library(String bookName,String author)
    {
        this.bookName=bookName;
        this.author=author;
    }
    public HashMap<Integer,Library> createLibraryMap(String booksInLibrary)
    {
        HashMap<Integer, Library> books = new HashMap<>();
        Arrays.stream(booksInLibrary.split("\\|"))
            .forEach(book -> {
                String[] infos = book.split(",");
                Library library = new Library(infos[1], infos[2]);
                books.put(Integer.parseInt(infos[0]), library);
            }
        );

        
        return books;
    }
    public HashMap<Integer,Integer> createUserMap(String borrowedUsers)
    {
        HashMap<Integer, Integer> bookToUser = new HashMap<>();
        Arrays.stream(borrowedUsers.split("\\|")).forEach( user ->
            {
                String[] bookIdToUserId = user.split(",");
                bookToUser.put(Integer.parseInt(bookIdToUserId[0]), Integer.parseInt(bookIdToUserId[1]));
            }
        );
        
        
        return bookToUser;
    }

    
    public String getQuery(String booksInLibrary,String borrowedUsers,String query)
    {
        Map<Integer, Library> libraryMap = createLibraryMap(booksInLibrary);
        HashMap<Integer, Integer> userMap = createUserMap(borrowedUsers);
        String result = null;
        String[] querySplit = query.split(",");
        switch(querySplit[0]){
            case "1":

                int bookId = Integer.parseInt(querySplit[1]);
                if(userMap.containsKey(bookId)){
                    result = String.format("No Stock\nIt is owned by %s\n", userMap.get(bookId));
                }else{
                    result = String.format("It is available\nAuthor is %s\n", libraryMap.get(bookId).author);
                }

                break;
            case "2":
                int userId = Integer.parseInt(querySplit[1]);
                Set<Integer> bookIds = userMap.entrySet().stream()
                        .filter(entry -> entry.getValue()== userId)
                        .map(Entry::getKey).collect(Collectors.toSet());
                result = bookIds.stream().map(id -> id + " " +libraryMap.get(id).bookName)
                        .collect(Collectors.joining("\n")) + "\n";
                break;
            case "3":
                Set<Integer> booksIds = libraryMap.entrySet().stream()
                        .filter(entry -> entry.getValue().bookName.equals(querySplit[1]))
                        .map(Entry::getKey)
                        .collect(Collectors.toSet());
                userMap.keySet().retainAll(booksIds);
                result = userMap.size()+" out\n"+(booksIds.size() - userMap.size())+" in\n";
                break;
            case "4":
                result = libraryMap.entrySet().stream()
                        .filter(entry -> entry.getValue().author.equals(querySplit[1]))
                        .map(entry -> entry.getValue().bookName)
                        .collect(Collectors.joining("\n")) + "\n";
                break;
            case "5":
                result = libraryMap.entrySet().stream()
                        .filter(entry -> entry.getValue().bookName.toLowerCase().contains(querySplit[1]))
                        .map(entry -> entry.getKey()+" "+entry.getValue().bookName)
                        .collect(Collectors.joining("\n")) + "\n";
                break;
        }
        return result;
    }
    
}

