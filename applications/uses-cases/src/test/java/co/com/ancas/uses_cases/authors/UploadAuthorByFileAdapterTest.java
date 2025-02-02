package co.com.ancas.uses_cases.authors;

import co.com.ancas.models.exceptions.BadRequestException;
import co.com.ancas.models.model.AuthorCreation;
import co.com.ancas.models.model.AuthorInformation;
import co.com.ancas.models.model.FileData;
import co.com.ancas.models.repositories.EmailRepositoryPort;
import co.com.ancas.models.repositories.UserRepositoryPort;
import co.com.ancas.uses_cases.email_template.FindEmailTemplateBySubjectAdapter;
import co.com.ancas.uses_cases.util.DownloadImageAdapter;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UploadAuthorByFileAdapterTest {
    @Mock
    private  FindEmailTemplateBySubjectAdapter findEmailTemplateBySubjectAdapter;
    @Mock
    private  DownloadImageAdapter downloadImageAdapter;
    @Mock
    private  CreateAuthorAdapter createAuthorAdapter;
    @Mock
    private  EmailRepositoryPort emailRepositoryPort;
    @Mock
    private  UserRepositoryPort userRepositoryPort;
    @InjectMocks
    private UploadAuthorByFileAdapter uploadAuthorByFileAdapter;
    private FileData fileData;
    private ArgumentCaptor<AuthorCreation> argumentCaptor;

    @BeforeEach
    void setUp() {
        fileData = FileData.builder()
                .nameFile("authors")
                .base64("TmFtZXxOYXRpb25hbGl0eXxCaXJ0aGRheXxCaW98SW1hZ2UsLCwKV2lsbGlhbSBTaGFrZXNwZWFyZXxFbmdsaXNofDIzLzA0LzE1NjR8RW5nbGlzaCBwbGF5d3JpZ2h0IGFuZCBwb2V0LCB3aWRlbHkgcmVnYXJkZWQgYXMgdGhlIGdyZWF0ZXN0IHdyaXRlciBpbiB0aGUgRW5nbGlzaCBsYW5ndWFnZSBhbmQgd29ybGQgbGl0ZXJhdHVyZS58aHR0cHM6Ly91cGxvYWQud2lraW1lZGlhLm9yZy93aWtpcGVkaWEvY29tbW9ucy9hL2EyL1NoYWtlc3BlYXJlLmpwZwpNYXJrIFR3YWlufEFtZXJpY2FufDMwLzExLzE4MzV8QW1lcmljYW4gYXV0aG9yIGFuZCBodW1vcmlzdCwgY2VsZWJyYXRlZCBmb3IgaGlzIGluc2lnaHRzIGludG8gaHVtYW4gbmF0dXJlIGFuZCBkZXBpY3Rpb24gb2YgbGlmZSBhbG9uZyB0aGUgTWlzc2lzc2lwcGkgUml2ZXIufGh0dHBzOi8vdXBsb2FkLndpa2ltZWRpYS5vcmcvd2lraXBlZGlhL2NvbW1vbnMvMC8wYy9NYXJrX1R3YWluX2J5X0FGX0JyYWRsZXkuanBnCkNoYXJsZXMgRGlja2Vuc3xFbmdsaXNofDA3LzAyLzE4MTJ8UHJvbGlmaWMgRW5nbGlzaCB3cml0ZXIgYW5kIHNvY2lhbCBjcml0aWMgd2hvIGhpZ2hsaWdodGVkIHRoZSBzdHJ1Z2dsZXMgb2YgdGhlIHdvcmtpbmcgY2xhc3MgZHVyaW5nIHRoZSBWaWN0b3JpYW4gZXJhLnxodHRwczovL3VwbG9hZC53aWtpbWVkaWEub3JnL3dpa2lwZWRpYS9jb21tb25zL3RodW1iL2EvYWEvRGlja2Vuc19HdXJuZXlfaGVhZC5qcGcvODAwcHgtRGlja2Vuc19HdXJuZXlfaGVhZC5qcGcKTGVvIFRvbHN0b3l8UnVzc2lhbnwwOS8wOS8xODI4fFJ1c3NpYW4gYXV0aG9yIGFuZCBwaGlsb3NvcGhlciwgcmVub3duZWQgZm9yIGhpcyBleHBsb3JhdGlvbiBvZiBtb3JhbGl0eSwgd2FyLCBhbmQgcGVhY2UgdGhyb3VnaCBlcGljIG5hcnJhdGl2ZXMufGh0dHBzOi8vdXBsb2FkLndpa2ltZWRpYS5vcmcvd2lraXBlZGlhL2NvbW1vbnMvdGh1bWIvYy9jNi9MLk4uVG9sc3RveV9Qcm9rdWRpbi1Hb3Jza3kuanBnLzgwMHB4LUwuTi5Ub2xzdG95X1Byb2t1ZGluLUdvcnNreS5qcGcKTWF5YSBBbmdlbG91fEFtZXJpY2FufDA0LzA0LzE5Mjh8QW1lcmljYW4gcG9ldCwgbWVtb2lyaXN0LCBhbmQgY2l2aWwgcmlnaHRzIGFjdGl2aXN0LCBjZWxlYnJhdGVkIGZvciBoZXIgYXV0b2Jpb2dyYXBoaWNhbCB3b3JrcyBjaHJvbmljbGluZyB0aGUgQWZyaWNhbiBBbWVyaWNhbiBleHBlcmllbmNlLnxodHRwczovL3VwbG9hZC53aWtpbWVkaWEub3JnL3dpa2lwZWRpYS9jb21tb25zL3RodW1iLzQvNGYvQW5nZWxvdV9hdF9DbGludG9uX2luYXVndXJhdGlvbl8lMjhjcm9wcGVkXzIlMjkuanBnLzgwMHB4LUFuZ2Vsb3VfYXRfQ2xpbnRvbl9pbmF1Z3VyYXRpb25fJTI4Y3JvcHBlZF8yJTI5LmpwZwo=")
                .extension("CSV")
                .build();
        argumentCaptor = ArgumentCaptor.forClass(AuthorCreation.class);
    }

    @Test
    void uploadAuthorByFileTest() throws MessagingException, IOException {
        //Arrange
        when(this.userRepositoryPort.findEmailsAdmins()).thenReturn(List.of("data"));
        when(downloadImageAdapter.downloadImage(anyString())).thenReturn("data");
        when(createAuthorAdapter.execute(argumentCaptor.capture())).thenReturn(new AuthorInformation());
        when(findEmailTemplateBySubjectAdapter.execute(anyString())).thenReturn("data");
        //Act
        uploadAuthorByFileAdapter.execute(fileData);
        //Assert
        assertNotNull(argumentCaptor.getValue());
    }

    @Test
    void uploadAuthorByFileTestWithException() throws MessagingException, IOException {
        //Arrange
        when(this.userRepositoryPort.findEmailsAdmins()).thenReturn(List.of("data"));
        when(downloadImageAdapter.downloadImage(anyString())).thenReturn("data");
        when(createAuthorAdapter.execute(argumentCaptor.capture())).thenThrow(new BadRequestException("Error"));
        when(findEmailTemplateBySubjectAdapter.execute(anyString())).thenReturn("data");
        //Act
        assertThrows(BadRequestException.class, () -> uploadAuthorByFileAdapter.execute(fileData));
    }

    @Test
    void uploadAuthorByFileTestWithException2()  {
        //Arrange
        fileData.setBase64("VGl0bGV8aXNibnxhdXRob3J8cHVibGlzaCBkYXRlfGdlbnJlfGF2YWlsYWJsZSBjb3BpZXN8Ymx1cmJ8YXZhaWxhYmxlfGltYWdlClBSSURFIEFORCBQUkVKVURJQ0V8OTc4MDE0MTQzOTUxOHxKQU5FIEFVU1RFTnwyOC8wMS8xODEzfFJPTUFOQ0V8NXxBIHN0b3J5IG9mIGxvdmUsIGNsYXNzLCBhbmQgZmFtaWx5IGluIGVhcmx5IDE5dGgtY2VudHVyeSBFbmdsYW5kLnxZRVN8aHR0cHM6Ly91cGxvYWQud2lraW1lZGlhLm9yZy93aWtpcGVkaWEvY29tbW9ucy9kL2Q0L1ByaWRlYW5kUHJlanVkaWNlQ0gzZGV0YWlsLmpwZwpFTU1BfDk3ODAxNDE0Mzk1ODd8SkFORSBBVVNURU58MjMvMTIvMTgxNXxST01BTkNFfDR8Rm9sbG93cyBFbW1hIFdvb2Rob3VzZSBhbmQgaGVyIG1hdGNobWFraW5nIGVmZm9ydHMgaW4gaGVyIHNvY2lhbCBjaXJjbGUufFlFU3xodHRwczovL3VwbG9hZC53aWtpbWVkaWEub3JnL3dpa2lwZWRpYS9jb21tb25zL3RodW1iL2YvZjIvRW1tYVRpdGxlUGFnZS5qcGcvMTgwcHgtRW1tYVRpdGxlUGFnZS5qcGcKQURWRU5UVVJFUyBPRiBIVUNLTEVCRVJSWSBGSU5OfDk3ODAxNDI0MzcxNzl8TUFSSyBUV0FJTnwxOC8wMi8xODg0fEFEVkVOVFVSRXw4fFRoZSBzdG9yeSBvZiBIdWNrIGFuZCBKaW3igJlzIGpvdXJuZXkgb24gdGhlIE1pc3Npc3NpcHBpIFJpdmVyLnxZRVN8aHR0cHM6Ly91cGxvYWQud2lraW1lZGlhLm9yZy93aWtpcGVkaWEvY29tbW9ucy90aHVtYi82LzYxL0h1Y2tsZWJlcnJ5X0Zpbm5fYm9vay5KUEcvMjAwcHgtSHVja2xlYmVycnlfRmlubl9ib29rLkpQRwpHUkVBVCBFWFBFQ1RBVElPTlN8OTc4MDE0MTQzOTU2M3xDSEFSTEVTIERJQ0tFTlN8MDEvMTEvMTg2MXxGSUNUSU9OfDZ8QSB5b3VuZyBib3kgbmFtZWQgUGlwIG1hdHVyZXMgdGhyb3VnaCBteXN0ZXJ5LCB3ZWFsdGgsIGFuZCBwZXJzb25hbCBncm93dGgufFlFU3xodHRwczovL3VwbG9hZC53aWtpbWVkaWEub3JnL3dpa2lwZWRpYS9jb21tb25zL3RodW1iLzgvOGQvR3JlYXRleHBlY3RhdGlvbnNfdm9sMS5qcGcvODAwcHgtR3JlYXRleHBlY3RhdGlvbnNfdm9sMS5qcGcKSSBLTk9XIFdIWSBUSEUgQ0FHRUQgQklSRCBTSU5HU3w5NzgwMzQ1NTE0NDAwfE1BWUEgQU5HRUxPVXwyNS8wMy8xOTY5fE1FTU9JUnw1fEFuZ2Vsb3XigJlzIGF1dG9iaW9ncmFwaGljYWwgYWNjb3VudCBvZiBoZXIgZWFybHkgbGlmZSBhbmQgdGhlIGhhcmRzaGlwcyBzaGUgZmFjZWQufFlFU3xodHRwczovL3VwbG9hZC53aWtpbWVkaWEub3JnL3dpa2lwZWRpYS9jb21tb25zL3RodW1iLzEvMTcvSV9Lbm93X1doeV90aGVfQ2FnZWRfQmlyZF9TaW5nc19mcm9udF9jb3ZlciUyQ18xOTY5X2ZpcnN0X2VkaXRpb24uanBnLzgwMHB4LUlfS25vd19XaHlfdGhlX0NhZ2VkX0JpcmRfU2luZ3NfZnJvbnRfY292ZXIlMkNfMTk2OV9maXJzdF9lZGl0aW9uLmpwZwpHQVRIRVIgVE9HRVRIRVIgSU4gTVkgTkFNRXw5NzgwMzQ1NTMxMDU2fE1BWUEgQU5HRUxPVXwwMy8wNC8xOTc0fE1FTU9JUnw0fEZvbGxvd3MgQW5nZWxvdeKAmXMgbGlmZSBhcyBhIHlvdW5nIGFkdWx0IGFuZCBzaW5nbGUgbW90aGVyLnxZRVN8aHR0cHM6Ly91cGxvYWQud2lraW1lZGlhLm9yZy93aWtpcGVkaWEvY29tbW9ucy9kL2QzL0dhdGhlcl9Ub2dldGhlcl9pbl9NeV9OYW1lX2Zyb250X2NvdmVyJTJDXzE5NzRfZmlyc3RfZWRpdGlvbi5qcGcK");
        when(this.userRepositoryPort.findEmailsAdmins()).thenReturn(List.of("data"));
        when(findEmailTemplateBySubjectAdapter.execute(anyString())).thenReturn("data");
        //Act
        assertThrows(BadRequestException.class, () -> uploadAuthorByFileAdapter.execute(fileData));
    }

}