package mvc.domain

import org.springframework.web.util.UriComponents
import org.springframework.web.util.UriComponentsBuilder

class PageMaker {
    var totalCount:Int = 0
        get() = field
        set(value) {
            field = value
            calcData()
        }

    var startPage:Int = 1
    var endPage:Int = 999
    var prevExists:Boolean = false
    var nextExists:Boolean = false

    var displayPageNum:Int = 10

    lateinit var criteria:Criteria

    private fun calcData(){
        endPage = (Math.ceil((criteria.page / displayPageNum.toDouble())) * displayPageNum).toInt()

        startPage = (endPage - displayPageNum) + 1

        var tempEndPage = (Math.ceil((totalCount/criteria.perPageNum.toDouble()))).toInt()

        if (endPage > tempEndPage) {
            endPage = tempEndPage
        }

        prevExists = startPage != 1
        nextExists = endPage * criteria.perPageNum < totalCount
    }

    fun makeQuery(page:Int):String {
        val uri:UriComponents = UriComponentsBuilder.newInstance().queryParam("page", page).queryParam("perPageNum", criteria.perPageNum).build()
        return uri.toUriString()
    }
}