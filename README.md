# Ders Kayıt Sistemi (Course Registration System)
 
Bu Java tabanlı program, bir ders kayıt sistemi simülasyonunu içerir. Bu sistem iki tür kullanıcıya sahiptir: Öğrenci ve Danışman. Öğrencilerin ders kaydı eklemesine, ders kaydı silmesine ve danışmana göndermesine olanak sağlar. Aynı zamanda danışman, onay bekleyen öğrenci kayıtlarını görüntüleyebilir, bunları onaylayabilir ve onaylanmış kayıtları CSV dosyası olarak dışarı aktarabilir.


## Kullanıcılar

Program başladığında kullanıcıları tanımak için bir giriş ekranı sunar. Sistem iki ana kullanıcı tipi destekler:

1. **Öğrenci**
2. **Danışman**


### Öğrenci İşlevleri

- **Ders Kaydı Ekleme**: Öğrenciler derslere kayıt olabilirler.
- **Ders Kaydı Silme**: Kayıtlı dersleri silebilirler.
- **Danışmana Gönderme**: Danışmana ders kayıtlarını gönderebilirler. Ancak önce en az 4 ders kaydı yapmak gereklidir.
- **Kaydı Onaylanan Öğrenci**: Kaydı onaylanmış öğrenci sisteme tekrar giriş yapamaz.

### Danışman İşlevleri

- **Onay Bekleyen Öğrencileri Görüntüleme**: Danışmanlar onay bekleyen öğrencilerin listesini görüntüleyebilirler.
- **Kayıtları Onaylama**: Danışmanlar öğrenci kimliği (ID) ile öğrenci ders kayıtlarını onaylayabilirler.
- **Onaylanmış Öğrencileri Dışa Aktarma**: Onaylanmış öğrenci kayıtlarını CSV formatında dışa aktarabilirler.

## Detaylar

- Danışman, öğrenci kayıtlarını ogrenciId üzerinden onaylar ve 4 ders birden onaylanır.
  
- Öğrenci, 4 ders kaydolmadan önce kayıtları danışmana gönderemez.
  
- Danışman onaylamayı tamamladığında öğrenci başka işlemler yapamaz.

- Sistemi kullanan birden fazla öğrenci, ancak bir tane danışman var

## Katkıda Bulunma

Projeye star vermek için aşağıdaki adımları takip edebilirsiniz:
1. Projenin GitHub sayfasına gidin.
2. Sayfanın sağ üst köşesinde bulunan "Star" düğmesine tıklayın.
3. Eğer oturum açmadıysanız, GitHub hesabınızla oturum açmanız istenecektir. Oturum açın veya yeni bir GitHub hesabı oluşturun.
4. "Star" düğmesine tekrar tıklayarak projeye star verebilirsiniz.




