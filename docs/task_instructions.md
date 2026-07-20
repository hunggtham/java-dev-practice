# Giáo Trình & Bài Tập Thực Hành: Backend Enterprise Java

Chào mừng bạn đến với khóa luyện tập mô phỏng quy trình làm việc thực tế của một Middle Backend Developer tại môi trường doanh nghiệp. Hệ thống đã được refactor với package `com.org.global.api`.

Dưới đây là 5 bài học và task luyện tập theo từng bước để bạn thành thạo kiến trúc framework này.

---

## Bài 1: Làm quen với Kiến trúc Enterprise (Routing & Annotations)
**Mục tiêu:** Hiểu cách hệ thống định tuyến (routing) API và vai trò của các Custom Annotation.
Trong hệ thống thực tế, thay vì chỉ dùng `@RestController`, dự án sẽ dùng thêm `@ElService` và `@ElDescription` để framework có thể log, tracking và quản lý quyền truy cập.

**Task thực hành:**
1. Tạo một class `HelloController` trong package `controller`. Đánh dấu nó bằng `@RestController` và `@RequestMapping("/api/global")`.
2. Tạo một phương thức `sayHello()`, trả về kiểu `Map<String, Object>`.
3. Gắn annotation `@GetMapping("/hello")` và `@ElService(key = "/api/global/hello")`.
4. Run project (`./mvnw spring-boot:run`) và dùng Postman hoặc Browser truy cập `http://localhost:8080/api/global/hello` để kiểm tra kết quả trả về.

---

## Bài 2: Kết nối & Truy vấn Cơ Sở Dữ Liệu (MyBatis & DAO)
**Mục tiêu:** Thực hành lấy dữ liệu từ DB thông qua MyBatis Interface (`@Mapper`). Chúng ta dùng H2 Database in-memory để không phải setup DB bên ngoài.

**Task thực hành:**
1. Mở file `schema.sql` (trong `src/main/resources`), tạo thêm bảng `products` gồm các cột `id`, `name`, `price`.
2. Mở file `data.sql`, `INSERT` khoảng 3-4 dòng dữ liệu mẫu vào bảng `products`.
3. Tạo class `ProductDao` trong package `dao`. Đánh dấu `@Mapper`.
4. Viết phương thức `List<Map<String, Object>> selectAllProducts();` và sử dụng annotation `@Select("SELECT * FROM products")` để truy vấn.

---

## Bài 3: Xử lý Dữ liệu và Value Object (DataUtil & VO)
**Mục tiêu:** Trong môi trường enterprise, chúng ta **không bao giờ** trả trực tiếp Map hoặc DB Entity cho frontend. Thay vào đó, dữ liệu phải được convert sang `VO` (Value Object) hoặc `DTO` bằng tiện ích chung.

**Task thực hành:**
1. Tạo class `R_ProductVo` (chữ R biểu thị Response) trong package `vo`, định nghĩa các trường `id`, `name`, `price` tương ứng với bảng DB, kèm theo Getter/Setter.
2. Tạo `ProductService` trong package `service`, có `@Resource` trỏ tới `ProductDao`.
3. Viết phương thức `getProducts()`, dùng `productDao.selectAllProducts()` lấy dữ liệu ra.
4. Gọi hàm tiện ích `DataUtil.convertList(listGoc, R_ProductVo.class)` để ép kiểu tự động từ List kết quả DB sang List VO.
5. Tạo `ProductController` để gọi `ProductService` và trả `List<R_ProductVo>` về cho người dùng. Đừng quên gắn `@ElDescription` trên Service.

---

## Bài 4: Tích hợp Open Source API linh hoạt
**Mục tiêu:** Hệ thống hiện tại thường không chứa toàn bộ data mà phải kết nối với các hệ thống (microservice) khác. Bạn cần cấu hình URL động thay vì hardcode.

**Task thực hành:**
1. Mở `application.yml`, trong block `open-api.urls` thêm một dòng: `todos: https://jsonplaceholder.typicode.com/todos`.
2. Mở file Enum `ApiType` (trong package `constant`), bổ sung hằng số `TODOS`.
3. Trong `OpenApiService`, thêm field `@Value("${open-api.urls.todos}") private String todosUrl;` và bổ sung vào lệnh `switch-case` để support `ApiType.TODOS`.
4. Tạo `TodoController` và `TodoService` để gọi `OpenApiService.fetchData(ApiType.TODOS, ...)` và lấy dữ liệu về hiển thị.

---

## Bài 5: Bài tập Tổng hợp - Multi-source Data Flow
**Mục tiêu:** Giả lập kịch bản khó khi bạn phải làm một chức năng mà dữ liệu nằm rải rác ở 2 nơi (Một nửa ở DB nội bộ, một nửa ở Service bên ngoài).

**Task thực hành (Masterpiece):**
1. Mở `application.yml`, thêm 1 API mở: `user-posts: https://jsonplaceholder.typicode.com/posts?userId=%d`.
2. Viết chức năng **"Lấy chi tiết 1 User"**: Gửi `userId` lên.
3. Ở Service: 
   - Dùng `PracticeDao` query ra thông tin cơ bản của User đó từ Database H2.
   - Kế tiếp, gọi `OpenApiService` (fetch theo `user-posts` với id) để lấy danh sách Bài Viết (Posts) của user đó từ JSONPlaceholder.
4. Tạo 1 Object tổng hợp `R_UserDetailVo` có chứa: Thông tin user + `List<PostVo>`.
5. Đóng gói dữ liệu và trả về cho Controller.

> [!TIP]
> Hãy tận dụng `AppLog.debug` và `AppLog.error` trong tất cả các class để quen với việc track log, giúp dễ debug khi deploy lên server thực tế.

Chúc bạn hoàn thành xuất sắc lộ trình! Mọi vấn đề về code mẫu (cách dùng DataUtil, OpenApiService) đều có thể xem tại `PracticeController.java` và `PracticeService.java`.
