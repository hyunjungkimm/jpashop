package jpabook.jpashop.web;

import jpabook.jpashop.domain.Book;
import jpabook.jpashop.domain.Item;
import jpabook.jpashop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ItemController {
    @Autowired
    ItemService itemService;

    @RequestMapping(value = "/items/new", method= RequestMethod.GET)
    public String createForm(){
        return "items/createItemForm";
    }

    @RequestMapping(value = "/items/new", method= RequestMethod.POST)
    public String create(Book item){
        itemService.saveItem(item);
        return "redirect:/items";
    }

    @GetMapping(value = "/items")
    public String list(Model model){
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "items/itemList";
    }
    //상품 수정폼
    @GetMapping(value = "/items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model model){
        Optional<Item> item = itemService.findById(itemId);
        model.addAttribute("item", item.get());
        return "items/updateItemForm";
    }
    //상품 수정
    @PostMapping(value = "/items/{itemId}/edit")
    public String updateItem(@ModelAttribute("item") Book item){
        itemService.saveItem(item);
        return "redirect:/items";
    }
}
