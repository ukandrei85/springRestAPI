package com.endava.springrestapi.service;

import com.endava.springrestapi.data.api.UserBookListDto;
import com.endava.springrestapi.data.api.UserDto;
import com.endava.springrestapi.data.response.MessageResponse;

import java.util.List;

public interface UserBooksListService {
    MessageResponse createUserBookList(UserBookListDto userBookListDto);
    MessageResponse  updateUserBookList(Integer id, UserBookListDto userBookListDto);
    MessageResponse deleteUserBookList(Integer id);
    UserBookListDto getASingleUserBookList(Integer id);
    List<UserBookListDto> getAllUserBookList();
}
