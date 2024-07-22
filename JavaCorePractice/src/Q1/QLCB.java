package Q1;

import java.util.ArrayList;
import java.util.Collection;

public class QLCB {

	private Collection<CanBo> danhSachCanBo;
	
	public QLCB() {
		danhSachCanBo = new ArrayList<CanBo>();
	}
	
	public void addCanBo(CanBo canbo) {
		
		
		
		danhSachCanBo.add(canbo);
	}
	
	public Collection<CanBo> findCanbo(String name){
		return danhSachCanBo.stream().filter(e -> e.getName().equalsIgnoreCase(name)).toList();
	}
	
	public Collection<CanBo> getDanhSachCanbo(){
		return danhSachCanBo;
	}
}
