package com.vutheduyet.exercise03.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.vutheduyet.exercise03.entity.StaffAccount;
import com.vutheduyet.exercise03.repository.StaffAccountRepository;
import com.vutheduyet.exercise03.service.StaffAccountService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StaffAccountServiceImpl implements StaffAccountService {
    private StaffAccountRepository staffAccountRepository;

    @Override
    public StaffAccount createStaffAccount(StaffAccount staffAccount) {
        return staffAccountRepository.save(staffAccount);
    }

    @Override
    public StaffAccount getStaffAccountById(String staffAccountId) {
        Optional<StaffAccount> optionalStaffAccount = staffAccountRepository.findById(staffAccountId);
        return optionalStaffAccount.orElse(null);
    }

    @Override
    public List<StaffAccount> getAllStaffAccounts() {
        return staffAccountRepository.findAll();
    }

    @Override
    public StaffAccount updateStaffAccount(StaffAccount staffAccount) {
        Optional<StaffAccount> optionalExistingStaffAccount = staffAccountRepository
                .findById(staffAccount.getStaffAccountId());
        if (optionalExistingStaffAccount.isPresent()) {
            StaffAccount existingStaffAccount = optionalExistingStaffAccount.get();
            existingStaffAccount.setFirstName(staffAccount.getFirstName());
            existingStaffAccount.setLastName(staffAccount.getLastName());
            existingStaffAccount.setPhoneNumber(staffAccount.getPhoneNumber());
            existingStaffAccount.setEmail(staffAccount.getEmail());
            existingStaffAccount.setPasswordHash(staffAccount.getPasswordHash());
            existingStaffAccount.setActive(staffAccount.isActive());
            existingStaffAccount.setImage(staffAccount.getImage());
            existingStaffAccount.setPlaceholder(staffAccount.getPlaceholder());
            existingStaffAccount.setUpdatedAt(LocalDateTime.now());

            StaffAccount updatedStaffAccount = staffAccountRepository.save(existingStaffAccount);
            return updatedStaffAccount;
        }

        return null; // Hoặc xử lý theo yêu cầu của bạn khi không tìm thấy tài khoản nhân viên tồn tại
    }

    @Override
    public void deleteStaffAccount(String staffAccountId) {
        staffAccountRepository.deleteById(staffAccountId);
    }
}
