package swsketch.utils;

public final class Pagenation {
	private int listSize = 3;		// 초기 값 (목록당 게시물 개수)
	private int rangeSize = 10;		// 초기 값 (페이지 범위)
	private int page;				// 현재 선택한 페이지
	private int range;				// 페이지 당 게시물의 개수
	private int listCnt;			// 총 게시물의 개수
	private int pageCnt;			// 총 페이지 개수
	private int startPage;			// 페이지 시작점
	private int endPage;			// 페이지 마지막점
	private int startIndex;			// 게시물 시작 번호
	private boolean prev;			// 이전 버튼 활성화
	private boolean next;			// 다음 버튼 활성화
	

	public int getListSize() {
		return listSize;
	}

	public int getRangeSize() {
		return rangeSize;
	}

	public int getPage() {
		return page;
	}


	public void setPage(int page) {
		this.page = page;
	}


	public int getRange() {
		return range;
	}


	public void setRange(int range) {
		this.range = range;
	}


	public int getListCnt() {
		return listCnt;
	}


	public void setListCnt(int listCnt) {
		this.listCnt = listCnt;
	}


	public int getPageCnt() {
		return pageCnt;
	}


	public void setPageCnt(int pageCnt) {
		this.pageCnt = pageCnt;
	}


	public int getStartPage() {
		return startPage;
	}


	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}


	public int getStartIndex() {
		return startIndex;
	}


	public void setStartList(int startList) {
		this.startIndex = startList;
	}


	public int getEndPage() {
		return endPage;
	}


	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}


	public boolean isPrev() {
		return prev;
	}


	public void setPrev(boolean prev) {
		this.prev = prev;
	}


	public boolean isNext() {
		return next;
	}


	public void setNext(boolean next) {
		this.next = next;
	}

	/**
	 * 페이징(페이지 당 게시물 리스트 기본 3개)
	 * @param page		현재 페이지 정보
	 * @param listCnt	게시물의 총 개수
	 */
	public void pageInfo(int page, int listCnt) {
		this.page = page;
		this.range = ((page-1)/rangeSize) + 1;
		this.listCnt = listCnt;

		//전체 페이지수 
		this.pageCnt = (int) Math.ceil(listCnt*1.0/listSize);

		//시작 페이지
		this.startPage = (range - 1) * rangeSize + 1 ;

		//끝 페이지
		this.endPage = range * rangeSize;

		//게시판 시작번호
		this.startIndex = (page - 1) *listSize;

		//이전 버튼 상태
		this.prev = range == 1 ? false : true;

		//다음 버튼 상태
		this.next = endPage > pageCnt ? false : true;

		if (this.endPage > this.pageCnt) {
			this.endPage = this.pageCnt;
			this.next = false;
		}
	}

	/**
 	 * 페이징(페이지 당 게시물 리스트 크기 지정)
	 * @param page		현재 페이지 정보
	 * @param listSize	현제 게시물 리스트 크기
	 * @param listCnt	게시물의 총 개수
	 */
	public void pageInfo(int page, int listSize, int listCnt) {
		this.page = page;
		this.listSize = listSize;
		this.range = ((page-1)/rangeSize) + 1;
		this.listCnt = listCnt;

		//전체 페이지수 
		this.pageCnt = (int) Math.ceil(listCnt*1.0/listSize);

		//시작 페이지
		this.startPage = (range - 1) * rangeSize + 1 ;

		//끝 페이지
		this.endPage = range * rangeSize;

		//게시판 시작번호
		this.startIndex = (page - 1) *listSize;

		//이전 버튼 상태
		this.prev = range == 1 ? false : true;

		//다음 버튼 상태
		this.next = endPage > pageCnt ? false : true;

		if (this.endPage > this.pageCnt) {
			this.endPage = this.pageCnt;
			this.next = false;
		}
	}
}
