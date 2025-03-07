### Muhammad Albyarto Ghazali
### 2306241695
### Kelas B

# Module 1
## Reflection 1

> After writing the unit test, how do you feel? How many unit tests should be made in a class? How to make sure that our unit tests are enough to verify our program? It would be good if you learned about code coverage. Code coverage is a metric that can help you understand how much of your source is tested. If you have 100% code coverage, does that mean your code has no bugs or errors? 

Dalam pengembangan aplikasi **EShop**, saya mengutamakan penulisan kode yang bersih dan terstruktur dengan menerapkan prinsip **clean code**. 
Proyek ini juga dibagi ke dalam empat paket utama,**controller, model, repository,** dan **service**,sehingga setiap komponen memiliki tanggung jawab 
yang jelas. Saya menerapkan **Single Responsibility Principle (SRP)** untuk memastikan pemisahan tugas antara **controller, service,** dan **repository**, serta menggunakan **konvensi penamaan yang bermakna**

Dari sisi fungsionalitas, aplikasi telah mendukung operasi **CRUD** untuk produk, termasuk fitur untuk menampilkan, menambah, mengedit, dan menghapus produk. 
Proses kerja aplikasi dibuat sistematis, di mana **repository** mengelola interaksi dengan database, **service layer** bertanggung jawab atas logika bisnis, dan **controller** menangani permintaan **HTTP**. 
Untuk meningkatkan user experience, saya juga menambahkan navigasi yang intuitif serta tombol aksi yang mempermudah penggunaan aplikasi.  

Dari aspek keamanan, saya telah mengambil beberapa langkah awal untuk meningkatkan perlindungan terhadap potensi ancaman. Salah satunya adalah penggunaan **UUID** sebagai **ID produk**, 
yang membantu memastikan identifikasi produk yang unik dan lebih aman dibandingkan pendekatan berbasis angka. Selain itu, validasi input dasar telah diterapkan untuk mengurangi risiko kesalahan dan eksploitasi.
Namun, masih ada beberapa aspek yang perlu diperbaiki, terutama dalam validasi parameter `@PathVariable` pada metode `deleteProductPage`, agar ID yang diterima tidak menyebabkan error 
atau menjadi celah keamanan. Saya juga berencana menambahkan token **CSRF** pada formulir untuk melindungi aplikasi dari serangan **Cross-Site Request Forgery (CSRF)**.  

Saat ini, aplikasi masih menggunakan struktur data _in-memory_, sehingga belum memiliki penyimpanan yang persisten. Meski begitu, saya telah menerapkan sanitasi input pada tampilan 
untuk mengurangi risiko serangan injeksi. Ke depannya, saya berencana mengintegrasikan anotasi validasi seperti `@Valid` pada **controller** guna memastikan integritas data. 
Selain itu, implementasi _custom exception handling_ akan ditambahkan agar pesan kesalahan lebih jelas dan proses debugging lebih efektif.  

## Reflection 2

> Suppose that after writing the CreateProductFunctionalTest.java along with the corresponding test case, you were asked to create another functional test suite that verifies the number of items in the product list. You decided to create a new Java class similar to the prior functional test suites with the same setup procedures and instance variables. What do you think about the cleanliness of the code of the new functional test suite? Will the new code reduce the code quality? Identify the potential clean code issues, explain the reasons, and suggest possible improvements to make the code cleaner!

Mengerjakan **unit test** dan **functional test** untuk aplikasi **EShop** memberikan wawasan lebih mendalam mengenai cara kerja sistem serta meningkatkan keyakinan terhadap kestabilan kode. 
**Unit test** berperan dalam memastikan setiap komponen berjalan sesuai fungsinya, sekaligus membantu mengidentifikasi edge case yang sebelumnya mungkin terlewat. 
Selain itu, menulis **unit test** juga memperdalam pemahaman tentang struktur aplikasi serta memungkinkan deteksi bug lebih awal. Setiap metode publik yang memiliki logika kompleks idealnya diuji,
meskipun jumlah pengujian dalam satu kelas tetap bergantung pada tingkat kerumitannya. _Code coverage_ dapat digunakan sebagai indikator sejauh mana pengujian mencakup kode, 
namun cakupan penuh (100%) tidak serta-merta menjamin bahwa aplikasi terbebas dari bug—kesalahan logika dan edge case yang belum terdeteksi masih bisa terjadi.  

Dalam hal **functional testing**, penyusunan test suite baru harus dirancang secara efisien agar tidak menghasilkan **code duplication** yang berlebihan. 
Jika hanya menyalin struktur pengujian sebelumnya, seperti dalam `CreateProductFunctionalTest`, maka pemeliharaan kode akan menjadi lebih sulit dan dapat melanggar prinsip _Don't Repeat Yourself (DRY)_. 
Untuk menghindari hal ini, penggunaan **kelas abstrak** atau **metode utilitas** dapat membantu menangani proses setup yang berulang, sehingga kode tetap terorganisir, modular, dan fleksibel 
untuk pengembangan lebih lanjut. Selain itu, penerapan pola desain seperti **Page Object Model** dapat meningkatkan keterbacaan serta keteraturan dalam pengujian fungsional, memastikan bahwa pengujian 
tetap dapat dikembangkan seiring bertambahnya fitur dalam aplikasi.  

Dari pengalaman ini, saya semakin memahami bahwa menulis **test** tidak hanya sekadar memastikan kode berfungsi, tetapi juga harus mempertimbangkan aspek keterbacaan, modularitas, dan kemudahan pemeliharaan.
Ke depannya, saya berencana untuk terus meningkatkan struktur pengujian agar lebih efisien, terorganisir, dan mampu menjaga **EShop** sebagai aplikasi yang **stabil, mudah dirawat, dan dapat berkembang** 
seiring waktu.

# Module 2
## Refleksi

> List the code quality issue(s) that you fixed during the exercise and explain your strategy on fixing them.
- Menghapus unused import `org.springframework.web.bind.annotation.` Pada kode program, perlu dilakukan penyederhanaan dengan menghapus impor yang tidak digunakan atau tidak diperlukan, seperti `import org.springframework.web.bind.annotation.*;`. Sebagai gantinya, hanya elemen-elemen spesifik dari package tersebut yang benar-benar digunakan dalam kode yang harus diimpor untuk meningkatkan keterbacaan dan efisiensi.
- Menghapus unnecessary modifier `public` pada metode-metode di dalam `ProductService.java`. Hal ini dikarenakan dalam Java, metode dalam antarmuka (`interface`) secara default sudah bersifat `public`, sehingga tidak perlu dinyatakan kembali secara eksplisit. Dengan melakukan perubahan ini, kode akan menjadi lebih bersih, ringkas, dan lebih sesuai dengan best practice dalam pengembangan perangkat lunak.

> Look at your CI/CD workflows (GitHub)/pipelines (GitLab). Do you think the current implementation has met the definition of Continuous Integration and Continuous Deployment? Explain the reasons (minimum 3 sentences)!

Berikut adalah tiga alasan mengapa implementasi CI/CD yang saya buat sudah sesuai dengan prinsip _Continuous Integration_ dan _Continuous Deployment_:  

#### 1. **Continuous Integration**  
   Implementasi yang saya lakukan telah memenuhi konsep _Continuous Integration_ karena setiap kali terdapat perubahan kode, baik melalui _push_ maupun _pull request_, sistem akan secara otomatis menjalankan proses _build_ dan _unit test_. Hal ini dikonfigurasi dalam file `ci.yml`, yang memastikan bahwa setiap perubahan diuji sebelum digabungkan ke dalam basis kode utama. Dengan adanya proses ini, potensi _bug_ dan masalah kompatibilitas dapat terdeteksi lebih awal, sehingga meningkatkan kualitas perangkat lunak secara keseluruhan.  

#### 2. **Peningkatan Keamanan dan Kualitas Kode**  
   Selain hanya menjalankan _build_ dan _test_, saya juga mengintegrasikan alat seperti `Scorecard` dan `PMD` dalam workflow. `Scorecard` digunakan untuk menilai aspek keamanan dalam repositori kode, sementara `PMD` berperan dalam menganalisis kode secara statis guna mendeteksi _code smells_, _bugs_, dan pelanggaran aturan pemrograman. Dengan adanya mekanisme evaluasi otomatis ini, setiap perubahan kode yang masuk akan selalu diperiksa terhadap potensi masalah keamanan dan kualitas kode, memastikan bahwa pengembangan tetap berjalan dengan standar yang tinggi.  

#### 3. **Continuous Deployment**  
   Dalam tahap _Continuous Deployment_, saya menggunakan layanan _Koyeb_, yang memungkinkan setiap perubahan pada branch _master_ langsung diterapkan ke _production environment_. Dengan kata lain, setiap kali ada _push_ atau _pull request_ yang diterima di branch utama, _Koyeb_ akan menangani proses _deployment_ secara otomatis. Pendekatan ini mempercepat _Software Development Cycle_, sehingga fitur dan perbaikan dapat segera diakses oleh pengguna tanpa perlu proses _deployment_ manual yang berpotensi memakan waktu.  

Dengan ketiga aspek ini—_automated testing and build_, _security and code quality enforcement_, serta _automatic deployment_—workflow yang saya buat saya rasa sudah sepenuhnya mencerminkan prinsip CI/CD yang efektif dan sesuai dengan best practice.

# Module 3
## Refleksi

> Explain what principles you apply to your project

### Single Responsibility Principle (SRP):
Saya menerapkan SRP dengan memastikan setiap kelas hanya memiliki satu tanggung jawab utama. Untuk itu, saya memisahkan kelas `ProductController` dan `CarController`, di mana masing-masing kelas hanya menangani permintaan HTTP terkait `Product` dan `Car` secara terpisah.  

### Open-Closed Principle (OCP): 
Saya mengimplementasikan OCP dengan memungkinkan ekstensi tanpa perlu mengubah kode yang sudah ada. Contohnya, `CarService` sebagai sebuah interface diimplementasikan oleh `CarServiceImpl`, sehingga saya dapat menambahkan fungsionalitas baru tanpa harus mengubah struktur dasar **CarService** itu sendiri.  

### Liskov Substitution Principle (LSP):
LSP memastikan bahwa subclass dapat menggantikan superclass tanpa mengganggu fungsionalitasnya. Dalam kode saya, `CarServiceImpl` meng-override metode yang ada di `CarService` tanpa mengubah perilaku yang sudah ada, sehingga dapat digunakan di mana pun `CarService` dibutuhkan.  

### Interface Segregation Principle (ISP):
Saya memecah interface besar menjadi beberapa interface yang lebih kecil dan spesifik. Dalam implementasi saya, interface `Product` dan `Car` dipisahkan agar tidak tergabung dalam satu interface besar, sehingga masing-masing hanya menangani tugas yang lebih terfokus dan sesuai dengan tanggung jawabnya.  

### Dependency Inversion Principle (DIP):
Prinsip ini diterapkan dengan memastikan bahwa kelas bergantung pada abstraksi, bukan pada implementasi konkret. Dalam kode saya, `CarController` mengakses metode dalam `CarServiceImpl` melalui interface `CarService`, bukan langsung menggunakan implementasi konkret `CarServiceImpl`, sehingga meningkatkan fleksibilitas dan mengurangi ketergantungan antar kelas.

> Explain the advantages of applying SOLID principles to your project with examples.

## Keuntungan Menerapkan Prinsip SOLID

### Single Responsibility Principle (SRP)  
Konsep : Setiap kelas hanya memiliki satu tanggung jawab.  
   - Membantu menjaga kode tetap mudah dikelola karena perubahan pada satu aspek sistem tidak akan memengaruhi aspek lain yang tidak terkait.

*Contoh:* Jika kita tidak memisahkan controller dalam aplikasi, `ProductController` bisa menjadi terlalu besar dan sulit dikelola. Memisahkan `CarController` dan `ProductController` sesuai dengan **SRP**, sehingga setiap kelas hanya menangani satu tugas.

### Open/Closed Principle (OCP)  
Konsep : *Software entities* (kelas, modul, fungsi, dll.) harus terbuka untuk extension tetapi tertutup untuk modifikasi.  
   - Memungkinkan kita menambah fitur tanpa mengubah kode yang sudah ada, sehingga mengurangi risiko adanya bug dan kode lebih stabil.  

### Liskov Substitution Principle (LSP)  
Konsep : Objek dari superclass harus dapat digantikan dengan objek dari subclassnya tanpa mengganggu fungsi program.  
   - Memastikan bahwa subtipe dapat digunakan secara bergantian dengan tipe induknya, meningkatkan penggunaan polimorfisme dan mencegah perilaku yang tidak diharapkan.  

### Interface Segregation Principle (ISP)  
Konsep : Kelas tidak boleh dipaksa untuk mengimplementasikan `interface` yang tidak digunakannya.  
   - Mendorong pembuatan `interface` yang lebih kecil dan lebih spesifik agar kelas tidak terbebani dengan metode yang tidak diperlukan.  

### Dependency Inversion Principle (DIP)  
Konsep : Modul tingkat tinggi tidak boleh bergantung pada modul tingkat rendah. Keduanya harus bergantung pada abstraksi.  
   - Mendorong ketergantungan dengan kelas abstraksi daripada ketergantungan antar kelas konkrit, sehingga sistem lebih fleksibel dan mudah dikelola.

*Contoh:* Alih-alih ``CarController`` langsung bergantung pada ``CarServiceImpl``, lebih baik ``CarController`` bergantung pada ``CarService`` (interface), sehingga implementasi ``CarServiceImpl`` bisa diganti tanpa mengubah ``CarController``.

### **Beberapa Manfaat dari Penerapan SOLID**  
- Maintainability: Kode lebih mudah dipahami, dimodifikasi, dan diperbarui.
- Flexibility: Kode lebih mudah beradaptasi dengan perubahan tanpa mengganggu sistem secara keseluruhan.  
- Scalability: Desain modular yang sesuai dengan prinsip SOLID memudahkan pengembangan lebih lanjut.  
- Lebih Readable: Kode lebih bersih dan mudah dipahami.  
- Lebih Mudah Ditest: Komponen yang mengikuti SOLID lebih mudah diuji secara terpisah.  

<br>

> Explain the disadvantages of not applying SOLID principles to your project with examples.
## **Kerugian Jika Tidak Menerapkan Prinsip SOLID**  

#### - Kode Kaku dan Rentan (Melanggar SRP) 
*Contoh:* Jika satu kelas memiliki terlalu banyak tanggung jawab, mengubah satu fitur bisa memengaruhi bagian lain yang tidak berhubungan, menyebabkan bug yang sulit dideteksi.  

#### - Sulit Diextend (Melanggar OCP) 
*Contoh:* Jika kita harus mengubah kode yang sudah ada setiap kali menambahkan fitur baru, maka risiko menimbulkan bug meningkat dan sistem menjadi tidak stabil.  

#### - Kesalahan dalam Penggunaan Subtipe (Melanggar LSP)  
*Contoh:* Jika subclass tidak mempertahankan perilaku dari superclass, substitusi bisa menyebabkan error yang tidak terduga dalam program.  

#### - Interface yang Tidak Optimal (Melanggar ISP)
*Contoh:* Jika sebuah kelas dipaksa untuk mengimplementasikan metode yang tidak digunakannya, maka akan ada banyak metode kosong atau tidak relevan dalam kelas tersebut, yang menyebabkan kode sulit dikelola.  

#### - Ketergantungan yang Kuat (Melanggar DIP)
*Contoh:* Jika `CarController` langsung bergantung pada `CarServiceImpl` tanpa abstraksi, maka sulit untuk mengganti atau menguji kode dengan mock, sehingga meningkatkan kompleksitas pengujian.  

### **Dampak Buruk Jika Tidak Menggunakan SOLID**  
- Kode Sulit Dibaca dan Dikelola: Struktur kode menjadi berantakan dan sulit dimengerti.
- Mengurangi efisiensi dalam development: Semakin lama, kode yang buruk akan sulit diperbaiki dan memakan lebih banyak waktu dalam pengembangan.  
- Sistem Sulit Dilakukan Test: Kode yang tidak modular akan sulit diuji secara terpisah, meningkatkan risiko bug yang tidak terdeteksi.  

## Module 4

### Reflection on TDD Workflow

Berdasarkan pertanyaan-pertanyaan reflektif yang diajukan oleh Percival (2017) dalam submodul "Principles and Best Practice of Testing", saya menemukan bahwa alur Test-Driven Development (TDD) memiliki manfaat tersendiri, antara lain:

- **Pemahaman Kebutuhan Lebih Baik:** Dengan memulai dari pembuatan tes terlebih dahulu, saya lebih terdorong untuk memahami secara detail apa yang dibutuhkan oleh fitur. Hal ini membantu saya memecah masalah menjadi skenario-skenario yang lebih kecil dan jelas.
- **Rasa Percaya Diri:** TDD memberikan rasa kepastian bahwa setiap fitur yang dibangun diuji secara menyeluruh. Setiap kali tes lulus, ada keyakinan bahwa fungsi tersebut bekerja sesuai dengan ekspektasi.
- **Deteksi Dini atas Bug:** Dengan menulis tes sebelum implementasi, saya lebih mudah mendeteksi bug atau edge case yang mungkin terlewat, sehingga mengurangi potensi regresi di masa mendatang.
- **Area Perbaikan:** Terkadang, menulis tes dalam TDD memerlukan refleksi mendalam mengenai skenario yang belum tertangani. Di masa depan, saya akan berusaha mendokumentasikan lebih banyak kasus tepi dan mengintegrasikan feedback loop yang lebih cepat untuk menyesuaikan tes dengan perubahan persyaratan.

Secara keseluruhan, TDD terbukti sangat berguna. Namun, saya perlu terus memperbaiki cakupan kasus uji dan memastikan bahwa setiap perubahan baru juga diiringi dengan pembaruan tes yang relevan.

### Reflection on the F.I.R.S.T. Principles in Unit Tests

Dalam pembuatan unit test di Tutorial, saya berusaha mengikuti prinsip F.I.R.S.T. (Fast, Independent, Repeatable, Self-validating, dan Timely). Berikut beberapa evaluasi dan area perbaikan:

- **Fast:** Tes saya umumnya berjalan cepat, namun ketika skenario menjadi lebih kompleks, saya perlu lebih pastikan bahwa tidak ada setup yang berlebihan yang dapat memperlambat eksekusi tes.
- **Independent:** Setiap tes telah dibuat sehingga tidak bergantung satu sama lain, namun saya harus lebih hati-hati dalam menggunakan data dan mock agar tidak terjadi tumpang tindih antara tes.
- **Repeatable:** Saya memastikan bahwa lingkungan tes selalu di-reset pada `@BeforeEach`. Namun, ada kalanya konfigurasi test environment dapat diperbaiki untuk menjamin repeatability yang lebih tinggi.
- **Self-validating:** Tes telah menggunakan assertion yang jelas untuk memvalidasi hasil. Saya harus terus memastikan bahwa setiap tes dapat dengan sendirinya menentukan apakah hasil yang diperoleh sesuai dengan yang diharapkan, tanpa intervensi manual.
- **Timely:** Unit test dibuat tepat ketika mengembangkan fitur, tetapi saya berharap untuk menerapkan lebih banyak tes pada tahap desain awal untuk menangkap lebih banyak edge case sejak awal pengembangan.

Pada umumnya, saya merasa sudah cukup baik dalam mengikuti prinsip F.I.R.S.T., namun akan lebih baik lagi jika saya dapat meningkatkan dokumentasi setiap tes serta terus melakukan review berkala untuk memastikan bahwa semua prinsip tetap terpenuhi seiring dengan bertambahnya kompleksitas fitur.

Dengan refleksi ini, saya dapat mengidentifikasi area yang perlu diperbaiki untuk pengembangan dan pengujian selanjutnya, sehingga kualitas kode dan efektivitas pengujian terus meningkat.
