package wang.wangby.trace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.wangby.repostory.Repository;
import wang.wangby.trace.dto.TraceOrderDto;
import wang.wangby.trace.model.TraceOrder;

import java.util.ArrayList;
import java.util.List;

@Service
public class TraceOrderService {

    @Autowired
    Repository repository;

    public List<TraceOrder> query(TraceOrderDto query) {
        List<TraceOrder> all = repository.select(new TraceOrder(), 0, 10000);
        List<TraceOrder> list = new ArrayList<>();
        for (TraceOrder o : all) {
            list.add(o);
        }
        return list;
    }

    public TraceOrder getBySellId(String sellId)  {
        for (TraceOrder tr : query(new TraceOrderDto())) {
            if (tr.getSellOrderId().equalsIgnoreCase(sellId)) {
                return tr;
            }
        }
        return null;
    }

    public void deleteById(long i) throws Exception {
        repository.delete(TraceOrder.class,i);
    }
}