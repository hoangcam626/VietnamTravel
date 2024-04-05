package com.travel.vietnamtravel.service.imp;

import com.travel.vietnamtravel.exception.CustomException;
import com.travel.vietnamtravel.repository.CommentRepo;
import com.travel.vietnamtravel.repository.ImageRepo;
import com.travel.vietnamtravel.repository.PlaceRepo;
import com.travel.vietnamtravel.repository.UserRepo;
import com.travel.vietnamtravel.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.travel.vietnamtravel.constant.Error.ERROR_NOT_EXIT;

@Service
@RequiredArgsConstructor
public class IsExitService {
    private final UserRepo userRepo;
    private final PlaceRepo placeRepo;
    private final ImageRepo imageRepo;

    public boolean isExitUser(Long id){
        if(!userRepo.existsById(id)){
            throw new CustomException(ERROR_NOT_EXIT);
        }
        return true;
    }

    public boolean isExitPlace(Long id){
        if(!placeRepo.existsById(id)){
            throw new CustomException(ERROR_NOT_EXIT);
        }
        return true;
    }


}
