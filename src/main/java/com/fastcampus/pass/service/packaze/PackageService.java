package com.fastcampus.pass.service.packaze;

import com.fastcampus.pass.repository.packaze.PackageEntity;
import com.fastcampus.pass.repository.packaze.PackageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackageService {
  private final PackageRepository packageRepository;

  public PackageService(PackageRepository packageRepository) {
    this.packageRepository = packageRepository;
  }

  public List<Package> getAllPackages(){
    // 패키지 이름으로 정렬하여 모든 패키지를 조회함.
    List<PackageEntity> bulkPassEntities = packageRepository.findAllByOrderByPackageName();

    return PackageModelMapper.INSTANCE.map(bulkPassEntities);
  }
}
