package com.endava.springrestapi.service;

import com.endava.springrestapi.data.api.UserBookListDto;
import com.endava.springrestapi.data.entitie.Book;
import com.endava.springrestapi.data.entitie.User;
import com.endava.springrestapi.data.response.MessageResponse;
import com.endava.springrestapi.exception.ResourceNotFoundException;
import com.endava.springrestapi.data.entitie.UserBookList;
import com.endava.springrestapi.repository.BookRepository;
import com.endava.springrestapi.repository.UserBooksRepository;
import com.endava.springrestapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserBooksListServiceImpl implements UserBooksListService{
    @Autowired
    private UserBooksRepository userBooksRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;



    @Override
    @Transactional
    public MessageResponse createUserBookList(UserBookListDto userBookListDto) throws ResourceNotFoundException {
       User user=userRepository.findById(userBookListDto.getUserId())
               .orElseThrow(()->new ResourceNotFoundException("Not found  User with id:"+userBookListDto.getUserId()));
       Book book= bookRepository.findById(userBookListDto.getBookId())
               .orElseThrow(()->new ResourceNotFoundException("Not found  User with id:"+userBookListDto.getBookId()));
       UserBookList userBookList=new UserBookList(user,book);
        userBooksRepository.save(userBookList);
        return new MessageResponse("UserBooksList created successfully");

    }

    @Override
    @Transactional
    public MessageResponse updateUserBookList(Integer id, UserBookListDto userBookListDto) throws ResourceNotFoundException {
        UserBookList userBookList=userBooksRepository.findById(id)
                        .orElseThrow(()->new ResourceNotFoundException("Not found  UserBookList with id:"+id));
        User user=userRepository.findById(userBookListDto.getUserId())
                .orElseThrow(()->new ResourceNotFoundException("Not found  User with id:"+userBookListDto.getUserId()));
        Book book= bookRepository.findById(userBookListDto.getBookId())
                .orElseThrow(()->new ResourceNotFoundException("Not found  User with id:"+userBookListDto.getBookId()));
        userBookList.setBook(book);
        userBookList.setUser(user);
        userBooksRepository.save(userBookList);
        return new MessageResponse("UserBooksList update successfully");
    }

    @Override
    @Transactional
    public MessageResponse deleteUserBookList(Integer id) throws ResourceNotFoundException{
        UserBookList userBookList=userBooksRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Not found  UserBookList with id:"+id));
        userBooksRepository.delete(userBookList);
        return new MessageResponse("UserBooksList deleted successfully");
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
        return new UserBookListDto(userBookList.getUser().getId(),userBookList.getBook().getId());
    }
}
