//package com.wnsud9771.entity.FIlterentity;
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@Entity
//@Table(name = "filter_items")
//@Getter 
//@Setter
//@NoArgsConstructor
//public class FilterItem {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    
//    private String name;
//    private String type;
//}
////
////import java.util.List;
////
////import com.wnsud9771.entity.Formatentity.FieldName;
////import com.wnsud9771.entity.Formatentity.FormatField;
////
////import jakarta.persistence.CascadeType;
////import jakarta.persistence.Entity;
////import jakarta.persistence.FetchType;
////import jakarta.persistence.GeneratedValue;
////import jakarta.persistence.GenerationType;
////import jakarta.persistence.Id;
////import jakarta.persistence.JoinColumn;
////import jakarta.persistence.ManyToOne;
////import jakarta.persistence.OneToMany;
////import lombok.Getter;
////import lombok.Setter;
////
////@Getter
////@Setter
////@Entity
////public class FilterItem {
////	@Id
////	@GeneratedValue(strategy = GenerationType.IDENTITY)
////	private Long id;
////	
////	//private String item_name; //아이템 명 == formatfiled의 필드 명 에서 받아서
////	private String item_alias; //아이템 별명
////	private String item_explain; //아이템 설명
////	private String item_type; //TYPE
////	
////	
////	@OneToMany(mappedBy = "filterItem", cascade = CascadeType.ALL)
////    private List<FormatField> formatField;
////	
////	@ManyToOne(fetch = FetchType.LAZY)
////	@JoinColumn(name = "fieldName_id")
////	private FieldName fieldName;
////}
