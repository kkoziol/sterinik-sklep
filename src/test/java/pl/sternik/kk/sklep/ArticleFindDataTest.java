package pl.sternik.kk.sklep;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class ArticleFindDataTest {

    @Test
    public void thatDateNotFound() throws Exception {
        // given:
        String opis = "Aaaaa 11-1111 bbbbb";
        // when:
        boolean result = Article.findDataWOpisie(opis);
        // then:
        assertThat(result).isFalse();
    }

    @Test
    public void thatDateFound() throws Exception {
        // given:
        String opis = "Aaaaa 11-11-1111 bbbbb";
        // when:
        boolean result = Article.findDataWOpisie(opis);
        // then:
        assertThat(result).isTrue();
    }

    @Test
    public void thatDateFoundOnSecondPositionFound() throws Exception {
        // given:
        String opis = "Aaaaa 11-1111 bbbbb 11-11-1111";
        // when:
        boolean result = Article.findDataWOpisie(opis);
        // then:
        assertThat(result).isTrue();
    }
    
    @Test
    @Ignore(value="Not fixed yet.")
    public void thatDateNotParsable() throws Exception {
        // given:
        String opis = "Aaaaa 11-32-1111 bbbbb";
        // when:
        boolean result = Article.findDataWOpisie(opis);
        // then:
        assertThat(result).isFalse();
    }


}
