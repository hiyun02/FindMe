package kopo.poly.service;

import kopo.poly.dto.FaceDTO;

import java.util.List;

public interface IGroupService {

    List<FaceDTO> getGroupList() throws Exception;

    FaceDTO createGroup(FaceDTO pDTO) throws Exception;

    void deleteGroup(FaceDTO pDTO) throws Exception;

}
