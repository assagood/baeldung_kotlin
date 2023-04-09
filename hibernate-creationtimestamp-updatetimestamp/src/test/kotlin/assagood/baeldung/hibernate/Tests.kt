package assagood.baeldung.hibernate

import assagood.baeldung.hibernate.entity.Book
import org.assertj.core.api.Assertions.assertThat
import org.hibernate.SessionFactory
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class HibernateTest {

    @Autowired
    lateinit var sessionFactory: SessionFactory

    @Test
    fun whenCreatingEntity_ThenCreatedOnIsSet() {
        val session = sessionFactory.openSession()
        session.beginTransaction()

        val book = Book()

        session.persist(book)
        session.transaction.commit()
        session.close()

        assertThat(book.createdOn).isNotNull
    }

    @Test
    fun whenCreatingEntity_ThenCreatedOnAndLastUpdatedOnAreBothSet() {
        val session = sessionFactory.openSession()
        session.beginTransaction()

        val book = Book()

        session.persist(book)
        session.transaction.commit()
        session.close()

        assertThat(book.createdOn).isNotNull
        assertThat(book.lastUpdatedOn).isNotNull
    }

    @Test
    fun whenUpdatingEntity_ThenLastUpdatedOnIsUpdatedAndCreatedOnStaysTheSame() {
        val session = sessionFactory.openSession()
        session.beginTransaction()

        val book = Book()
        session.persist(book)
        session.flush()

        val createdOnAfterCreation = book.createdOn
        val lastUpdatedOnAfterCreation = book.lastUpdatedOn

        val newName = "newName"
        book.title = newName
        session.transaction.commit()
        session.close()

        val createdOnAfterUpdate = book.createdOn
        val lastUpdatedONAfterUpdate = book.lastUpdatedOn

        assertThat(book.title).isEqualTo(newName)
        assertThat(createdOnAfterUpdate).isNotNull
        assertThat(lastUpdatedONAfterUpdate).isNotNull
        assertThat(createdOnAfterCreation).isEqualTo(createdOnAfterUpdate)
        assertThat(lastUpdatedOnAfterCreation).isNotEqualTo(lastUpdatedONAfterUpdate)
    }
}
