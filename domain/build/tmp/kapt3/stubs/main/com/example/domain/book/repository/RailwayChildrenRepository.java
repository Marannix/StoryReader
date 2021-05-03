package com.example.domain.book.repository;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0004H&J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0004H&\u00a8\u0006\u0007"}, d2 = {"Lcom/example/domain/book/repository/RailwayChildrenRepository;", "", "getBook", "Lio/reactivex/Observable;", "", "book", "getBookFromLocalStorage", "domain"})
public abstract interface RailwayChildrenRepository {
    
    @org.jetbrains.annotations.NotNull()
    public abstract io.reactivex.Observable<java.lang.String> getBook(@org.jetbrains.annotations.NotNull()
    java.lang.String book);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.String getBookFromLocalStorage(@org.jetbrains.annotations.NotNull()
    java.lang.String book);
}