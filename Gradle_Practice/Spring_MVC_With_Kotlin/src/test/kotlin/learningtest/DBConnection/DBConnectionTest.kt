package learningtest.DBConnection

import org.junit.Before
import org.junit.Test

interface DBConnectionTest {
    @Before fun setupConnection()
    @Test fun testConnection()
}