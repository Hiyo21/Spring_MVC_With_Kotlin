package mvc.controller

import mvc.domain.RestfulSampleVO
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("actionRestful")
class SampleRestController{

    // currently Kotlin doesn't specify annotation attribute as single value!!
    //@RequestMapping(value="/{name}", method = arrayOf(RequestMethod.GET), produces = arrayOf("application/json"))
    @GetMapping(value = "/{name}", produces = arrayOf("application/json"))
    fun actionWithJSON(@PathVariable name:String):RestfulSampleVO{
        val vo = RestfulSampleVO(name, "Address1")
        return vo
    }

    @RequestMapping(value = "/{name}.xml", method= arrayOf(RequestMethod.GET), produces = arrayOf("application/xml"))

    fun actionWithXML(@PathVariable name:String):RestfulSampleVO{
        val vo = RestfulSampleVO(name, "Address2")
        return vo
    }
}