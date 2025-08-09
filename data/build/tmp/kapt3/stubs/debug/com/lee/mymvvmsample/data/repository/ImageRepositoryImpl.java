package com.lee.mymvvmsample.data.repository;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J4\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0096@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006\u0011"}, d2 = {"Lcom/lee/mymvvmsample/data/repository/ImageRepositoryImpl;", "Lcom/lee/mymvvmsample/domain/repository/ImageRepository;", "apiService", "Lcom/lee/mymvvmsample/data/network/ImageApiService;", "imageMapper", "Lcom/lee/mymvvmsample/data/mapper/ImageMapper;", "(Lcom/lee/mymvvmsample/data/network/ImageApiService;Lcom/lee/mymvvmsample/data/mapper/ImageMapper;)V", "searchImages", "Lkotlin/Result;", "Lcom/lee/mymvvmsample/domain/model/ImageSearchResult;", "query", "", "page", "", "perPage", "searchImages-BWLJW6A", "(Ljava/lang/String;IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "data_debug"})
public final class ImageRepositoryImpl implements com.lee.mymvvmsample.domain.repository.ImageRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.lee.mymvvmsample.data.network.ImageApiService apiService = null;
    @org.jetbrains.annotations.NotNull()
    private final com.lee.mymvvmsample.data.mapper.ImageMapper imageMapper = null;
    
    @javax.inject.Inject()
    public ImageRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.lee.mymvvmsample.data.network.ImageApiService apiService, @org.jetbrains.annotations.NotNull()
    com.lee.mymvvmsample.data.mapper.ImageMapper imageMapper) {
        super();
    }
}