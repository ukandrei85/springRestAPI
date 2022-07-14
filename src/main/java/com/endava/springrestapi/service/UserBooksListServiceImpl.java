package com.endava.springrestapi.service;

import com.endava.springrestapi.data.api.UserBookListDto;
import com.endava.springrestapi.data.response.MessageResponse;
import com.endava.springrestapi.exception.ResourceNotFoundException;
import com.endava.springrestapi.data.entities.UserBookList;
import com.endava.springrestapi.repository.UserBooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserBooksListServiceImpl implements UserBooksListService{
    @Autowired
    private UserBooksRepository userBooksRepository;



    @Override
    public MessageResponse createUserBookList(UserBookListDto userBookListDto) {
        UserBookList userBookList=new UserBookList();
        userBookList.setUserBookId(userBookListDto.getBookId());
        userBookList.setUserBookId(userBookListDto.getUserId());
        userBooksRepository.save(userBookList);
        return new MessageResponse("UserBooksList created successfully");

    }

    @Override
    public MessageResponse updateUserBookList(Integer id, UserBookListDto userBookListDto) {
        UserBookList userBookList=userBooksRepository.findById(id)
                        .orElseThrow(()->new ResourceNotFoundException("Not found  UserBookList with id:"+id));
        userBookList.setUserBookId(userBookListDto.getBookId());
        userBookList.setUserBookId(userBookListDto.getUserId());
        userBooksRepository.save(userBookList);
        return new MessageResponse("UserBooksList created successfully");
    }

    @Override
    public MessageResponse deleteUserBookList(Integer id) throws ResourceNotFoundException{
        UserBookList userBookList=userBooksRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Not found  UserBookList with id:"+id));
        userBooksRepository.delete(userBookList);
        return new MessageResponse("UserBooksList created successfully");
    }

    @Override
    public UserBookListDto getASingleUserBookList(Integer id) throws ResourceNotFoundException {
        UserBookList userBookList=userBooksRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Not found  UserBookList with id:"+id));
        return mapEntityToApi(userBookList);
    }

    @Override
    public List<UserBookListDto> getAllUserBookList() {
        return userBooksRepository.findAll().stream().map(this::mapEntityToApi).toList();
    }
    public UserBookListDto mapEntityToApi(UserBookList userBookList){
        return new UserBookListDto(userBookList.getUser().getUserId(),userBookList.getBook().getId());
    }
}
