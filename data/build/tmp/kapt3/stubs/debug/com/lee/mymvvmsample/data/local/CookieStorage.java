package com.lee.mymvvmsample.data.local;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001R\u0018\u0010\u0002\u001a\u00020\u0003X\u00a6\u000e\u00a2\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007\u00a8\u0006\b"}, d2 = {"Lcom/lee/mymvvmsample/data/local/CookieStorage;", "", "cookie", "", "getCookie", "()Ljava/lang/String;", "setCookie", "(Ljava/lang/String;)V", "data_debug"})
public abstract interface CookieStorage {
    
    @org.jetbrains.annotations.NotNull()
    public abstract java.lang.String getCookie();
    
    public abstract void setCookie(@org.jetbrains.annotations.NotNull()
    java.lang.String p0);
}