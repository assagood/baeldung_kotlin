package assagood.baeldung.hibernate.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.Instant

@Entity
class Book {
    @Id
    @GeneratedValue
    var id: Long = 0
    var title: String? = null

    @CreationTimestamp
    var createdOn: Instant = Instant.now()

    @UpdateTimestamp
    var lastUpdatedOn: Instant = Instant.now()
}
