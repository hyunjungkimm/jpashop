package jpabook.jpashop.service;

import jpabook.jpashop.domain.Item;
import jpabook.jpashop.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ItemService {

    @Autowired
    ItemRepository itemRepository;

    public void saveItem(Item item){
        itemRepository.save(item);
    }

    public List<Item> findItems(){
        return itemRepository.findAll();
    }

    public Optional<Item> findById(Long itemId){
        return itemRepository.findById(itemId);
    }
}
