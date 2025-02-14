Muhammad Albyarto Ghazali
2306241695
Kelas B

## Reflection 1

Dalam pengembangan aplikasi **EShop**, saya mengutamakan penulisan kode yang bersih, terstruktur, dan mudah dikelola dengan menerapkan prinsip **clean code** dan **secure coding**. 
Untuk menjaga keteraturan serta meningkatkan skalabilitas, proyek ini dibagi ke dalam empat paket utama—**controller, model, repository,** dan **service**—sehingga setiap komponen memiliki tanggung jawab 
yang jelas. Saya menerapkan **Single Responsibility Principle (SRP)** untuk memastikan pemisahan tugas antara **controller, service,** dan **repository**, serta menggunakan **konvensi penamaan yang bermakna**
agar kode lebih mudah dipahami. Selain itu, prinsip **enkapsulasi** diterapkan guna membatasi akses langsung ke data dan meningkatkan modularitas aplikasi.  

Dari sisi fungsionalitas, aplikasi telah mendukung operasi **CRUD** untuk produk, termasuk fitur untuk menampilkan, menambah, mengedit, dan menghapus produk. 
Proses kerja aplikasi dibuat sistematis, di mana **repository** mengelola interaksi dengan database, **service layer** bertanggung jawab atas logika bisnis, dan **controller** menangani permintaan **HTTP**. 
Untuk meningkatkan pengalaman pengguna, saya juga menambahkan navigasi yang intuitif serta tombol aksi yang mempermudah penggunaan aplikasi.  

Dari aspek keamanan, saya telah mengambil beberapa langkah awal untuk meningkatkan perlindungan terhadap potensi ancaman. Salah satunya adalah penggunaan **UUID** sebagai **ID produk**, 
yang membantu memastikan identifikasi produk yang unik dan lebih aman dibandingkan pendekatan berbasis angka. Selain itu, validasi input dasar telah diterapkan untuk mengurangi risiko kesalahan dan eksploitasi.
Namun, masih ada beberapa aspek yang perlu diperbaiki, terutama dalam validasi parameter **@PathVariable** pada metode **deleteProductPage**, agar ID yang diterima tidak menyebabkan error 
atau menjadi celah keamanan. Saya juga berencana menambahkan token **CSRF** pada formulir untuk melindungi aplikasi dari serangan **Cross-Site Request Forgery (CSRF)**.  

Saat ini, aplikasi masih menggunakan struktur data **in-memory**, sehingga belum memiliki penyimpanan yang persisten. Meski begitu, saya telah menerapkan sanitasi input pada tampilan 
untuk mengurangi risiko serangan injeksi. Ke depannya, saya berencana mengintegrasikan anotasi validasi seperti **@Valid** pada **controller** guna memastikan integritas data. 
Selain itu, implementasi **custom exception handling** akan ditambahkan agar pesan kesalahan lebih jelas dan proses debugging lebih efektif.  

## Reflection 2

Mengerjakan **unit test** dan **functional test** untuk aplikasi **EShop** memberikan wawasan lebih mendalam mengenai cara kerja sistem serta meningkatkan keyakinan terhadap kestabilan kode. 
**Unit test** berperan dalam memastikan setiap komponen berjalan sesuai fungsinya, sekaligus membantu mengidentifikasi edge case yang sebelumnya mungkin terlewat. 
Selain itu, menulis **unit test** juga memperdalam pemahaman tentang struktur aplikasi serta memungkinkan deteksi bug lebih awal. Setiap metode publik yang memiliki logika kompleks idealnya diuji,
meskipun jumlah pengujian dalam satu kelas tetap bergantung pada tingkat kerumitannya. **Code coverage** dapat digunakan sebagai indikator sejauh mana pengujian mencakup kode, 
namun cakupan penuh (100%) tidak serta-merta menjamin bahwa aplikasi terbebas dari bug—kesalahan logika dan edge case yang belum terdeteksi masih bisa terjadi.  

Dalam hal **functional testing**, penyusunan test suite baru harus dirancang secara efisien agar tidak menghasilkan **code duplication** yang berlebihan. 
Jika hanya menyalin struktur pengujian sebelumnya, seperti dalam **CreateProductFunctionalTest**, maka pemeliharaan kode akan menjadi lebih sulit dan dapat melanggar prinsip **Don't Repeat Yourself (DRY)**. 
Untuk menghindari hal ini, penggunaan **kelas abstrak** atau **metode utilitas** dapat membantu menangani proses setup yang berulang, sehingga kode tetap terorganisir, modular, dan fleksibel 
untuk pengembangan lebih lanjut. Selain itu, penerapan pola desain seperti **Page Object Model** dapat meningkatkan keterbacaan serta keteraturan dalam pengujian fungsional, memastikan bahwa pengujian 
tetap dapat dikembangkan seiring bertambahnya fitur dalam aplikasi.  

Dari pengalaman ini, saya semakin memahami bahwa menulis **test** tidak hanya sekadar memastikan kode berfungsi, tetapi juga harus mempertimbangkan aspek keterbacaan, modularitas, dan kemudahan pemeliharaan.
Ke depannya, saya berencana untuk terus meningkatkan struktur pengujian agar lebih efisien, terorganisir, dan mampu menjaga **EShop** sebagai aplikasi yang **stabil, mudah dirawat, dan dapat berkembang** 
seiring waktu.
