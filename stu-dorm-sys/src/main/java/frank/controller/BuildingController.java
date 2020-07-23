package frank.controller;

import frank.model.Building;
import frank.model.DictionaryTag;
import frank.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/building")
public class BuildingController {

    @Autowired
    private BuildingService buildingService;

    @GetMapping("/query")
    public Object query(Building building){
        List<Building> buildings = buildingService.query(building);
        return buildings;
    }

    @GetMapping("/queryById")
    public Object queryById(Integer id){
        Building building = buildingService.queryById(id);
        return building;
    }

    @PostMapping("/add")
    public Object insert(@RequestBody Building building){
        buildingService.insert(building);
        return null;
    }

    @PostMapping("/update")
    public Object updateById(@RequestBody Building building){
        buildingService.updateById(building);
        return null;
    }

    @GetMapping("/delete")
    public Object deleteByIds(@RequestParam("ids") List<Integer> ids){
        buildingService.deleteByIds(ids);
        return null;
    }

    @GetMapping("/queryAsDict")
    public Object queryAsDict(String dictionaryKey){
        List<DictionaryTag> tags = buildingService.queryAsDict(dictionaryKey);
        return tags;
    }
}
