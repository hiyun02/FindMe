package kopo.poly.service;

import kopo.poly.dto.NoticeDTO;

import java.util.List;

public interface IImgService {

    // 게시글 이미지를 스토리지에 저장하고 URL을 반환함
    List<String> uploadImagesToStorage(NoticeDTO pDTO, String userId);

}