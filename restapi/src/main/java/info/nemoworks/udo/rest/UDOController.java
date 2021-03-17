package info.nemoworks.udo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import info.nemoworks.udo.service.UdoService;
import okhttp3.ResponseBody;

@RestController
public class UDOController {

    @Autowired
    private UdoService service;

    @GetMapping(name = "/")
    public ResponseBody<Udo> udos{
        return service.
    }
    
}
