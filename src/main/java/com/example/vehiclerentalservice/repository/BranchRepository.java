package com.example.vehiclerentalservice.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.vehiclerentalservice.model.Branch;
import org.springframework.stereotype.Repository;


@Repository
public class BranchRepository {
    private final Map<String, Branch> branches = new HashMap<>();

    public void addBranch(String name, Branch branch) {
        branches.put(name, branch);
    }

    public Branch getBranch(String name) {
        return branches.get(name);
    }

    public List<Branch> getAllBranches() {
        return new ArrayList<>(branches.values());
    }
}
