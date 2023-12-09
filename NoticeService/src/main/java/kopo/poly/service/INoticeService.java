package kopo.poly.service;

import kopo.poly.dto.NoticeDTO;

import java.util.List;

public interface INoticeService {

    /**
     * 실종자 게시판 카테고리 별 조회
     */
    List<NoticeDTO> getNoticeList(String category);

    /**
     * 실종자 상세 정보 가져오기
     *
     * @param pDTO 공지사항 상세 가져오기 위한 객체
     * @param type 조회수 증가여부(true : 증가, false : 증가안함
     */
    NoticeDTO getNoticeInfo(NoticeDTO pDTO, boolean type) throws Exception;

    /**
     * 실종자 정보 저장하기
     *
     * @param pDTO 실종자 정보 저장하기 위한 객체
     */
    void insertNoticeInfo(NoticeDTO pDTO);

    /**
     * 실종자 정보 수정하기
     *
     * @param pDTO 공지사항 수정하기 위한 객체
     */
    void updateNoticeInfo(NoticeDTO pDTO) throws Exception;

    /**
     * 해당 실종자 정보 삭제하기
     *
     * @param pDTO 공지사항 삭제하기 위한 객체
     */
    void deleteNoticeInfo(NoticeDTO pDTO);

}