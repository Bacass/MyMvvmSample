# MyMvvmSample
My kotlin MVVM Sample 

### 기본구조 ###

* Kotiln + MVVM + Retrofit2 + Gson + Glide + Koin
* api 는 NetworkService.kt 파일에서 추가한 후 NetworkRepository.kt 파일에서 한번 더 작업한 것을 사용하는 구조다.
* 의존성주입(DI) 는 Koin 을 이용했다.
* 새로운 ViewModel 을 추가할때마다 MyApplication.kt 에서 ViewModel 을 등록해줘야 한다.
* SharedPreference 는 kotpref 를 이용했다.
* Log 는 Timber 를 이용했다.

### 코딩 컨벤션 ###

* Android Studio 디폴트 포맷을 사용합니다.
* 클래스,변수,함수등 명명 방법은 카멜케이스를 따라주세요.

### 깃 운영 플로우 ###

* 마스터 브랜치는 항상 컴파일에러 없이 빌드성공 가능한 상태로 커밋해주세요.
* 작업은 최대한 브랜치를 생성해서 작업하고, 결과물만 마스터로 머지해주세요.
* 마스터에 브랜치 머지후에 특별한 이유 없으면 해당 브랜치는 항상 제거 해주세요.

### 커밋 로그 컨벤션 ###

* '커밋 타입:내용' 형식으로 커밋로그를 남길것
* 커밋 타입
	- feat: 새 기능, 기존 기능 개선, 버그수정.
	- doc: 문서, 주석
	- res: 코드외에 리소스만 추가/수정인 경우(주로 res, assets 폴더쪽)
* 예시1 feat: 날짜 변환 로직 추가
* 예시2 doc: 기획문서 업데이트.

### !!! ###
* 위 내용을 최대한 지켜가며 작업해주세요.
* 위 내용은 언제든 추가/수정 가능합니다.

### ###
<div>
<img src="https://user-images.githubusercontent.com/23072075/80856672-cbbeb200-8c86-11ea-88cd-259416be19df.gif" width="30%"></img>
</div>
