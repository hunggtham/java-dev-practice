package org.global.api.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.List;
import java.util.stream.Collectors;

public class DataUtil {

    private static final ModelMapper modelMapper = new ModelMapper();

    static {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    /**
     * Chuyển đổi một đối tượng thành một đối tượng thuộc class khác
     */
    public static <S, T> T convert(S source, Class<T> targetClass) {
        if (source == null) return null;
        return modelMapper.map(source, targetClass);
    }

    /**
     * Chuyển đổi một danh sách (List) sang danh sách của class khác
     */
    public static <S, T> List<T> convertList(List<S> sourceList, Class<T> targetClass) {
        if (sourceList == null || sourceList.isEmpty()) {
            return null; // Giữ đúng convention trả về null nếu rỗng giống hệ thống cũ, hoặc tuỳ chọn return rỗng
        }
        return sourceList.stream()
                .map(source -> modelMapper.map(source, targetClass))
                .collect(Collectors.toList());
    }

    /**
     * Kiểm tra list có dữ liệu không
     */
    public static boolean isNotEmpty(List<?> list) {
        return list != null && !list.isEmpty();
    }
}
