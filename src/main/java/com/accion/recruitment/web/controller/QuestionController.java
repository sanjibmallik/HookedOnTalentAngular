package com.accion.recruitment.web.controller;

import org.springframework.stereotype.Controller;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 13/01/17 00:11 AM#$
 */

@Controller
public class QuestionController {



    //Duplicate code from requirement page.. caused running  problem 
/*

    @Autowired
    QuestionService questionService;

    @ApiOperation(value = "Created ", httpMethod="POST"
            , notes = "Creates Question")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Question Created "),
            @ApiResponse(code = 500, message = "Internal Server Error")})

    @RequestMapping(value = RequirementURIConstants.REQUIREMENT_CREATE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> createRequirement(@RequestBody Positions positions) {

        return new ResponseEntity<Object>(ReqtEnums.REQT_CREATED.ResponseMsg(), HttpStatus.OK      );
    }
*/


}
