## MyMvvmSample
Kotlin + MVVM 샘플 프로젝트

## 멀티 모듈 구조
- app: Presentation(UI)
  - Activity/Fragment/ViewModel, Hilt 진입점
  - DI 바인딩: `CookieStorageImpl -> CookieStorage`, `UserPreferencesImpl -> UserPreferences`
- data: Data layer
  - Retrofit/OkHttp/Interceptor, DTO/Mapper, Repository 구현
  - DI 모듈: `NetworkModule`, `RepositoryModule`
- domain: Domain layer
  - UseCase, Repository 인터페이스, Domain Model, Failure/Either, Preferences 인터페이스

의존성 방향
- app → domain, app → data, data → domain (domain은 어떤 모듈에도 의존하지 않음)

## DI(Hilt)
- Application: `@HiltAndroidApp` MyApplication
- app 모듈: `AppModule`
  - `@Binds` CookieStorageImpl, UserPreferencesImpl
- data 모듈: `NetworkModule`, `RepositoryModule`
  - OkHttpClient/Retrofit/ImageApiService 제공, ImageRepository 바인딩

## 에러 모델 표준화
- Failure(sealed class)
  - Network, Server(code,message), NoData, InvalidInput(message), Unknown(message)
- Either<L, R>
  - Left(Failure) / Right(Success)
- Repository/UseCase 서명
  - `Either<Failure, Result>`로 일관화

## 네트워크
- Retrofit with OkHttp + Gson
- Sandwich ApiResponse 사용
  - ApiService: `suspend fun ...(): ApiResponse<DTO>`
  - Repository: ApiResponse 분기 → Either(Failure/Success) 변환

## UI 상태 관리
- ViewModel: StateFlow 기반 `UiState`
- Fragment: `repeatOnLifecycle(STARTED)`로 수집

## 환경설정/스토리지
- CookieStorage(인터페이스, data에 정의) ↔ CookieStorageImpl(app, AppPrefs 사용)
- UserPreferences(인터페이스, domain에 정의) ↔ UserPreferencesImpl(app, AppPrefs 사용)

## 로깅/이미지/기타
- Timber, Glide, Kotpref

## 개발 규칙(요약)
- MVVM + UseCase + Repository + Mapper
- 도메인 순수 Kotlin, 외부/프레임워크 의존 차단
- 멀티 모듈로 경계 강제
- 빌드타입별 서버/키는 BuildConfig로 주입

## 기여 가이드
- 브랜치 전략: 기능별 브랜치 → PR → 머지 후 브랜치 정리
- 커밋 컨벤션: `feat|fix|refactor|docs|chore: 내용`

