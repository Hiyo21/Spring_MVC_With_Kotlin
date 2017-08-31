package mvc.domain

open class SearchCriteria: Criteria() {
    lateinit var searchType:String
    lateinit var keyword:String
}