package com.helin.springboot.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @NotBlank
    @Size(min = 4, max = 15, message = "Kullanıcı adınız 3-15 karakter arasında olmalıdır.")
    private String username;

    @NotBlank
    @Size(min = 6, max = 56, message = "Şifreniz 6-56 karakter arasında olmalıdır.")
    @Pattern(regexp="^(?=.*[A-Za-z])(?=.*\\d).{6,64}$", message="Şifre harf ve rakam içermeli")
    private String password;

    @NotBlank
    @Email(message = "Lütfen Geçerli bir e-posta adresi giriniz.")
    private String email;

    @NotBlank
    @Pattern(regexp = "^\\+?[1-9]\\d{7,14}$", message = "Telefon numaranız + ile ve 8-15 hane olmalıdır. (örn: +905551112233) arasında boşluk bırakmayınız.")
    private String phone;
}
