# Hoàn tất khởi tạo môi trường luyện tập (Walkthrough)

Tôi đã hoàn thiện toàn bộ việc thiết lập môi trường luyện tập Java Backend (Java 8, Spring Boot 2.7.x) mô phỏng kiến trúc nội bộ của hệ thống doanh nghiệp (Enterprise Framework).

## Những cập nhật mới nhất (Refactor & Kiến trúc)
- **Đổi tên Package:** Toàn bộ dự án đã được đổi tên từ `com.shinhanglobal.gmsa` sang `com.org.global` để loại bỏ sự phụ thuộc vào dữ liệu video gốc. Class khởi chạy chính là `GlobalApiApplication`.
- **OpenApiService:** Thay vì hardcode URL, việc gọi API ngoài giờ đây tuân thủ đúng chuẩn Enterprise:
  1. Các URL được cấu hình tại `application.yml` (`open-api.urls`).
  2. Bất kỳ khi nào cần gọi data, bạn dùng phương thức `openApiService.fetchData(ApiType.USERS, ...)` thay vì điền URL thủ công.

## Cấu trúc dự án hiện tại (`com.org.global.api`)

### 1. Common Utils (Tiện ích dùng chung)
- **`DataUtil`**: Chứa hàm `convertList` sử dụng ModelMapper để ép kiểu từ dữ liệu nhận được sang dạng `Vo` (Value Object).
- **`HttpCommonUtil`**: Giao tiếp HTTP với các dịch vụ bên ngoài bằng Spring `RestTemplate`.
- **`AppLog`**: Dùng để log thông tin dạng debug/info giống thư viện log in-house.

### 2. Database và DAO
- Dự án sử dụng **H2 Database in-memory** kết hợp với **MyBatis** (`PracticeDao`).
- Dữ liệu và bảng được nạp sẵn mỗi khi khởi chạy thông qua `schema.sql` và `data.sql`.

### 3. Annotations đặc thù
- Sử dụng `@ElService`, `@ElDescription` nhằm mô phỏng hệ thống Inswave/ProWorks thường dùng tại các tổ chức tín dụng.

## Hướng dẫn tiếp theo
Môi trường đã hoàn toàn độc lập và sạch sẽ. Bạn hãy mở file [task_instructions.md](file:///Users/mac/.gemini/antigravity-ide/brain/6679888a-714e-481e-bfc1-f57f28b174e7/task_instructions.md) lên để bắt đầu luyện tập với 5 bài học thực tiễn dành cho Middle Backend Developer mà tôi đã biên soạn!
