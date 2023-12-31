package com.ogooogoo.server.pets.category;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum Species {
    unknown("unknown", Arrays.asList()),
    dog("강아지", Arrays.asList("믹스견", "고든 세터", "골든 두들", "골든 리트리버", "그레이트 데인", "그레이트 스위스 마운틴 도그",
            "그레이트 피레니즈", "그레이 하운드", "그로넨달", "그리스 셰퍼드", "그리스 헤어하운드", "그린란드견", "글렌 오브 이말 테리어", "기슈견", "까나리오",
            "꼬동 드 툴레아", "나폴리탄 마스티프", "노르보텐 스피츠", "노르웨이안 룬트훈트", "노르웨이안 버훈트", "노르웨이안 엘크 하운드", "노르위치 테리어",
            "노르포크 테리어", "노바 스코셔 덕 톨링 리트리버", "노포크 테리어", "뉴펀들랜드", "닥스훈트", "달마시안", "대니시 스웨디시 팜독", "댄디 딘몬트 테리어",
            "더치 셰퍼드", "도고 까나리오", "도고 아르헨티노", "도그 드 보르도", "도베르만", "도사견", "동경견(경주개)", "드레버", "디어 하운드", "라고토 로마뇰로",
            "라사압소", "라이카", "라지 먼스터랜더", "라케노이즈", "라포니안 허더", "래브라도 리트리버", "랫 테리어", "러셀 테리어", "러스키 토이", "러처", "레드본 쿤하운드",
            "레온베르거", "레이크랜드 테리어", "로디지안 리지백", "로첸", "로트와일러", "루마니안 미오리틱 셰퍼드 독", "리틀 라이언 독", "마스티프", "마운틴 커", "말리노이즈",
            "말티즈", "맨체스터 테리어", "무디", "미니어처 불 테리어", "미니어처 슈나우저", "미니어처 핀셔", "미니어쳐 아메리칸 셰퍼드", "바베트", "바센지", "바셋 포브 드 브레타뉴",
            "바셋하운드", "배틀링턴 테리어", "버거 피카드", "버니즈 마운틴 독", "베들링턴 테리어", "벨지안 셰퍼드 독", "보더 콜리", "보더 테리어", "보르도 마스티프", "보르조이",
            "보비에 드 플란더스", "보스롱(뷰세런)", "보스턴 테리어", "보어보엘", "보이킨 스패니얼", "복서", "볼로네즈", "불 마스티프", "불 테리어", "불개", "불도그", "불리 쿠타",
            "브라질리언 가드 독", "브라코 이탈리아노", "브라크 뒤 부르보네", "브로홀머", "브뤼셀 그리펀", "브리어드", "브리타니 스파니엘", "블랙 러시안 테리어", "블랙 앤 탄 쿤하운드",
            "블러드 하운드", "블루 피카르디 스패니얼", "블루틱 쿤하운드", "비글", "비숑 프리제", "비어디드 콜리", "비즐라", "쁘띠 바셋 그리펀 벤딘", "쁘띠 브라바콘", "사모예드",
            "살루키", "삽살개", "샤를로스 울프독", "샤페이", "샤펜도스", "서섹스 스페니얼", "세인트 버나드", "센트럴 아시안 오브차카", "셔틀랜드 쉽독", "소프트 코티드 휘튼 테리어",
            "슈나우저", "슈나우저(미니어쳐)", "슈나우저(스탠다드)", "슈나우저(자이언트)", "스무스 폭스 테리어", "스웨디시 라프훈트", "스위디쉬 발훈트", "스카이 테리어",
            "스코티시 디어하운드", "스코티시 테리어", "스키퍼키", "스태포드셔 불 테리어", "스탠다드 푸들", "스테비훈", "스패니쉬 그레이 하운드", "스페니쉬 마스티프",
            "스페니쉬 워터 독", "스피노네 이달리아노", "스피츠", "스핑크스", "슬로바키안 와이어헤어드 포인터", "슬루기", "시바견", "시베리안 허스키", "시추", "시코쿠견",
            "실리함 테리어", "실키 테리어", "아나톨리안 마스티프", "아나톨리안 셰퍼드독", "아메리칸 밴도지", "아메리칸 불리", "아메리칸 스태포드셔 테리어",
            "아메리칸 스테그 하운드", "아메리칸 이키타견", "아메리칸 에스키모 독", "아메리칸 워터 스패니얼", "아메리칸 잉글리시 쿤하운드", "아메리칸 코커 스파니엘", "아메리칸 폭스하운드",
            "아메리칸 헤어리스 테리어", "아이리시 레드 앤 화이트 세터", "아이리시 세터", "아이리시 소프트코티드 휘튼 테리어", "아이리시 울프 하운드", "아이리시 워터 스패니얼",
            "아이리시 테리어", "아이슬랜딕 쉽독", "아자와크", "아크라이트", "아크바시", "아키타견", "아펜첼러 제넨훈트", "아펜핀셔", "아프간 하운드", "알래스칸 말라뮤트",
            "알렌테조 마스티프", "에스트렐라 마운틴 독", "에어데일 테리어", "엔틀버쳐 마운틴 독", "오브차카", "오스트레일리안 셰퍼드 독", "오스트레일리안 캐틀 독",
            "오스트레일리안 켈피", "오스트레일리안 테리어", "오터 하운드", "올드 잉글리시 쉽독", "와이마라너", "와이어 폭스 테리어", "와이어헤어드 비즐라", "와이어헤어드 포인팅 그리폰",
            "요크셔 테리어", "요키", "웨스트 하일랜드 화이트 테리어", "웰시 스프링거 스파니엘", "웰시 코기", "웰시 코기 카디건", "웰시 코기 펨브로크", "웰시 테리어", "유라시아",
            "이비잔 하운드", "이탈리안 그레이 하운드", "이탈리엔 세구죠", "이탈리안 코로소 독", "잉글리시 마스티프", "잉글리시 불독", "잉글리시 세터", "잉글리시 스프링거 스패니얼",
            "잉글리시 코카 스패니얼", "잉글리시 포인터", "잉글리시 폭스하운드", "자이언트 슈나우저", "잭 러셀 테리어", "저먼 롱헤어드 포인터", "저먼 셰퍼드 독", "저먼 쇼트헤어드 포인터",
            "저먼 스피츠", "저먼 와이어헤어드 포인터", "저먼 핀셔", "저먼 헌팅 테리어", "제주개", "제패니즈 스피츠", "제페니스 친", "진돗개", "차우차우", "차이니스 크레스티드",
            "체서피크 베이 리트리버", "체스키 테리어", "체코슬로바키안 울프독", "치누크", "치와와", "친", "카네 코르소", "카렐리안 베어 독", "카발리에 킹 찰스 스패니얼", "카이",
            "카이견", "카타호울라 레오파트 독", "캉갈", "캐롤리나 독", "컬리 코티드 리트리버", "케니스펜더", "케리 블루 테리어", "케언 테리어", "케이넌 독", "케이스혼트", "코리안 마스티프",
            "코몬도르", "코카 스파니엘", "코카시언 셰퍼드 독", "콜리", "콜리(러프)", "콜리(스무스)", "쿠바츠", "크레타 하운드", "크로아티안 쉽독", "클럼버 스패니얼", "키슈", "키스흔드",
            "킹 찰스 스파니엘", "타이 리지백 독", "타이완 독(포르모산 마운틴 도그)", "테디 루즈벨트 테리어", "테르뷰렌", "토른쟈크", "토이 맨체스터 테리어", "토이 폭스 테리어",
            "통일개", "트라이 하운드", "트랜시바니안 하운드", "트리잉 워커 쿤하운드", "트리잉 테네시 브린들", "티베탄 마스티프", "티베탄 스패니얼", "티베탄 테리어", "파라오 하운드",
            "파슨 러셀 테리어", "파피용", "패터데일 테리어", "퍼그", "페로 드 프레사 카나리오", "페키니즈", "포르셀엔", "포트투갈 워터 독", "포르투갈 포덴고", "포르투갈 포인터",
            "포메라니안", "포인터", "폭스 테리어", "폴리시 로랜드 쉽독", "폼피츠", "푸델포인터", "푸들", "푸미", "풀리", "풍산개", "프레사 까나리오", "프렌치 불독", "프렌치 스패니얼",
            "플랫 코티드 리트리버", "피니시 라프훈트", "피니시 스피츠", "피레니안 마운틴 독", "피레니안 셰퍼드", "필드 스패니얼", "필라 브라질레이로", "핏 불 테리어", "하바니즈(하바네제)",
            "해리어", "호바와트", "훗카이도견", "휘핏")),

    cat("고양이", Arrays.asList("세상에 하나뿐인 믹스", "네벨룽", "노르웨이 숲고양이", "데본렉스", "돈스코이", "라가머핀", "라이코이", "라팜", "라팜 쇼트헤어", "랙돌",
            "러시안 블루", "맹크스", "먼치킨", "먼치킨 롱헤어", "메인쿤", "미뉴엣(나폴레옹)", "미뉴엣 롱헤어", "민스킨", "발리니즈", "뱅갈", "버만", "버미즈", "버밀라", "봄베이",
            "브라질리안 쇼트헤어", "브리티시 롱헤어", "브리티시 쇼트헤어", "사바나", "샤트룩스", "샴", "세렝게티", "셀커크 렉스", "셀커크 렉스 롱헤어", "소말리", "소코케", "스노우 슈",
            "스코티시 스트레이트", "스코티시 폴드", "스코티시 폴드 롱헤어", "스키프 토이 밥테일", "스핑크스", "시베리안 고양이", "싱가푸라", "싸이프러스 아프로디테", "아라비안 마우",
            "아메리칸 밥테일", "아메리칸 밥테일 쇼트헤어", "아메리칸 쇼트헤어", "아메리칸 와이어헤어", "아메라킨 컬", "아비시니안", "엑조틱 쇼트헤어", "오리엔탈 고양이",
            "오스트레일리안 미스트", "오시캣", "오호스 아즐레스", "오호스 아즐레스 롱헤어", "요크 초콜릿", "유러피안 버미즈", "유러피앗 숏헤어", "이그조틱 고양이", "이집션 마우",
            "자바니즈", "재패니즈 밥테일", "저먼 렉스", "쵸시", "카오마니", "캘리포니아 스팽글드", "컬러포인트 숏헤어", "코니시 렉스", "코랏", "코리안 쇼트헤어", "쿠라리안 밥테일",
            "킴릭", "타이", "터키시 앙고라", "터키시반", "토이거", "통키니즈", "페르시안", "피터볼드", "픽시 밥", "픽시 밥 롱헤어", "하바나 브라운", "하이랜더", "하이랜드 폴드", "히말라얀"));

    private final String petType;

    private final List<String> speciesList;

    public String getPetType() {
        return petType;
    }

    public List<String> getSpeciesList() {
        return speciesList;
    }

    Species(String petType, List<String> speciesList) {
        this.petType = petType;
        this.speciesList = speciesList;
    }

    public static Species getSpecies(String speciesName) {
        return Arrays.stream(Species.values())
                .filter(species -> species.getSpeciesList().contains(speciesName))
                .findFirst()
                .orElse(Species.unknown);
    }

    public static String getAllSpecies() throws Exception {
        Map<String, List<String>> allSpecies = new HashMap<>();
        for (Species species : values()) {
            allSpecies.put(species.getPetType(), species.getSpeciesList());
        }

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(allSpecies);
    }

}
