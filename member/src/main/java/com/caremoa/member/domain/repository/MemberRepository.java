package com.caremoa.member.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.caremoa.member.domain.model.Member;


/**
* @packageName    : com.caremoa.member.domain.repository
* @fileName       : MemberRepository.java
* @author         : 이병관
* @date           : 2023.05.14
* @description    : Member Repository
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2023.05.14        이병관       최초 생성
*/
public interface MemberRepository extends JpaRepository<Member, Long> {

}
/**
+-------------------+----------------------------------------------------+------------------------------------+
| 메서드 이름 키워드|  샘플                                              |     설명                           |
+-------------------+----------------------------------------------------+------------------------------------+
| And               | findByEmailAndUserId(String email, String userId)  | 여러필드를 and 로 검색             |
| Or                | findByEmailOrUserId(String email, String userId)   | 여러필드를 or 로 검색              |
| Between           | findByCreatedAtBetween(Date fromDate, Date toDate) | 필드의 두 값 사이에 있는 항목 검색 |
| LessThan          | findByAgeGraterThanEqual(int age)                  | 작은 항목 검색                     |
| GreaterThanEqual  | findByAgeGraterThanEqual(int age)                  | 크거나 같은 항목 검색              |
| Like              | findByNameLike(String name)                        | like 검색                          |
| IsNull            | findByJobIsNull()                                  | null 인 항목 검색                  |
| In                | findByJob(String … jobs)                           | 여러 값중에 하나인 항목 검색       |
| OrderBy           | findByEmailOrderByNameAsc(String email)            | 검색 결과를 정렬하여 전달          |
+-------------------+----------------------------------------------------+------------------------------------+
    // -- 조회(단건인 경우)
    // Optional<Member> findByMemberIdAndMemberPwd(String memberId, String memberPwd);	
	
    // -- 조회(다건인 경우 Page 처리)
    // Page<Member> findByEmailAndName(String email, String name, Pageable pageable);
	
	// -- 조회(다건인 경우 전체) 
	// 위의 예제에서는 SQL 쿼리 및 메서드 매개 변수를 동일한 이름을 갖도록 정의했지만 값 문자열이 동일한 한 필요하지 않습니다.
	// List<Member> findByEmailAndName(@Param("email")String param, @Param("name")String userName, Pageable pageable);
	
	// JPQL SAMPLE
	// -- Class로 Return
	// @Query("select new map(m.username, m.address, m.age) from User m")
    // Page<Map<String, Object>>test(Pageable pageable);
	// @Query("select new com.sample.dto.JoinTest(m.code, m.codeName, m.groupCode, n.groupName)"+
	//        " from CommonCodeDetail m, CommonCode n" +
	// 	   " where m.groupCode = n.groupCode")
    // Page<JoinTest>jointest(Pageable pageable);
	// 
	// -- Map으로 Return
	// @Query("select new map(m.code as code , m.codeName as codeName , m.groupCode as groupCode, n.groupName as groupName)"+
	//        " from CommonCodeDetail m, CommonCode n" +
	// 	   " where m.groupCode = n.groupCode" +
	// 	   "   and m.groupCode = :groupCode")
	// Page<Map<String, Object>>jointest1(@Param("groupCode") String groupCode, Pageable pageable);
    //
	// -- JPA Query가 아닌 Native Query를 쓰는 경우
    // @Query(value = "SELECT * FROM Users u WHERE u.status = :status and u.name = :name", 
    //        nativeQuery = true)
    // User findUserByStatusAndNameNamedParamsNative(@Param("status") Integer status, @Param("name") String name);
    //
	// -- PQL 또는 SQL 쿼리의 where 절에 IN(또는 NOT IN) 키워드가 포함된 경우 
	//    Collection을 매개 변수로 사용하는 쿼리 메서드를 정의
	// @Query(value = "SELECT u FROM User u WHERE u.name IN :names")
    // List<User> findUserByNameList(@Param("names") Collection<String> names);
	//
	// -- 데이터를 수정하는 저장소 메서드는 select 쿼리와 비교하여 두 가지 차이점이 있습니다 - 
	// @Modifying 주석이 있으며 물론 JPQL 쿼리는 select 대신 update를 사용합니다.
	// @Modifying
    // @Query("update User u set u.status = :status where u.name = :name")
    // int updateUserSetStatusForName(@Param("status") Integer status, @Param("name") String name);
    // 
	//
	// -- 삽입 작업을 수행하려면 INSERT가 JPA 인터페이스의 일부가 아니기 때문에 @Modifying을 적용하고 네이티브 쿼리를 사용해야합니다. 
	// @Modifying
    // @Query(
    //   value = 
    //     "insert into Users (name, age, email, status) values (:name, :age, :email, :status)",
    //   nativeQuery = true)
    // void insertUser(@Param("name") String name, @Param("age") Integer age, 
    //   @Param("status") Integer status, @Param("email") String email);
	//
	// -- 삭제 
    // @Modifying
    // @Query("delete from Book b where b.title=:title")
    // void deleteBooks(@Param("title") String title);
**/