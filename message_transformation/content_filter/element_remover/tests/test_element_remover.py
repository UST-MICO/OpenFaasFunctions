from element_remover.handler import *
from json import loads


class TestContentFilter:

    def test_handle(self):
        req = '{\
            "specversion" : "0.2",\
            "type" : "io.github.ust.mico.result",\
            "source" : "/router",\
            "id" : "A234-1234-1234",\
            "time" : "2019-05-08T17:31:00Z",\
            "contenttype" : "application/json",\
            "data" : {\
                "key" : {"testKey1": 1, "testKey2": 2, "testKey3": 3}}\
            }'
        resp = handle(req)
        dict_resp = loads(resp)
        for k in keys:
            assert k not in dict_resp['data'].keys()