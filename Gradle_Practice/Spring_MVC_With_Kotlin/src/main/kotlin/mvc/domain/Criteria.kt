package mvc.domain

open class Criteria(page: Int = 1, perPageNum: Int = 10) {
    // Overriding getter and setter in Kotlin : right under field declaration
    var page: Int = page
        get() = field
        set(value) = if (value <= 0) field = 1 else field = value

    var perPageNum:Int = perPageNum
        get() = field
        set(value) = if(value <= 0 || value > 100) field = 10 else field = value

    var pageStart:Int = (page - 1) * perPageNum
}