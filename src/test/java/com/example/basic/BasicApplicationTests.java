package com.example.basic;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.basic.Controllers.ProfilesControllerImpl;
import com.example.basic.Controllers.UsersControllerImpl;
import com.example.basic.Entities.Profile;
import com.example.basic.Entities.Users;
import com.example.basic.Services.ProfileService;
import com.example.basic.Services.UsersService;
import com.example.basic.Services.UsersServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
class BasicApplicationTests {
	
	private MockMvc mockMvc;
	
	ObjectMapper objectMapper = new ObjectMapper();
	ObjectWriter objectWriter = objectMapper.writer();
	
	@InjectMocks
	private ProfilesControllerImpl profilesController;
	@InjectMocks
	private UsersControllerImpl usersController;
	
	@Mock
	private ProfileService profileService;
	@Mock
	private UsersService userService;
	
	@MockBean
	private UsersServiceImpl usrSrv;
	@MockBean
   	private ProfileServiceImpl prfSrv;
	@BeforeAll
	void setUp() {
		MockitoAnnotations.openMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(usersController).build();
	}
	
	Users user_1 = new Users("user1",1234,"user1",new Profile("profile1"),1,"active","01-01-2022","01-01-2022","true");
	Users user_2 = new Users("user2",1234,"user2",new Profile("profile2"),1,"active","01-01-2022","01-01-2022","true");
	Users user_3 = new Users("user3",1234,"user3",new Profile("profile3"),1,"active","01-01-2022","01-01-2022","true");
	Profile profile_1 = new Profile("dadsa");
    	Profile profile_2 = new Profile("dweax");
	/*
	CompletableFuture<List<Users>> record = new ArrayList<>(Arrays.asList(user_1,user_2,user_3));
	@Test
	void contextLoads() {
	}
	
	@Test
	public void GetAllMappingSucess() throws Exception {
		
		Mockito.when(userService.Getall()).thenReturn( record);
		
		mockMvc.perform(MockMvcRequestBuilders
				.get("/client/")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(3)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].username", is(user_1.getUsername())))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].username", is(user_1.getUsername())))
				.andExpect(MockMvcResultMatchers.jsonPath("$[2].username", is(user_1.getUsername())));
	}
	 */
     @Test
    public void getProfileTest() throws Exception {
        //given
        given(prfSrv.findByIdAsync(Mockito.anyLong()))
                .willReturn(
                        CompletableFuture.<Optional<Profile>>completedFuture(Optional.ofNullable(profile_1))
                );
        //when

        ObjectMapper mapr = new ObjectMapper();

        String body = mapr.writeValueAsString(profile_1);

        String expected = "";
        MvcResult response =
                this.mockMvc.perform(get("http://localhost:8090/api/v1/profiles/1").accept(MediaType.APPLICATION_JSON) )
                        .andExpect(request().asyncStarted())
                        .andExpect(status().is2xxSuccessful())
                        .andReturn();

        this.mockMvc.perform(asyncDispatch(response))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(body))
        ;
    }	
    @Test
    public void allUserTest() throws Exception {

        List<Users> all = new ArrayList<Users>();
        all.add(user_1);
        all.add(user_2);

        given(usrSrv.Getall())
                .willReturn(
                        CompletableFuture.completedFuture(all)
                );
        //when


        String expected = "";

        this.mockMvc.perform(get("http://localhost:9090/api/v1/users/").accept(MediaType.APPLICATION_JSON) )
                .andDo(print())
                .andExpect(content().string(expected))
                .andExpect(status().is2xxSuccessful())
        ;
        //then

    }

    @Test
    public void getUserTest() throws Exception {
        //given
        given(usrSrv.findByIdAsync(Mockito.anyLong()))
                .willReturn(
                        CompletableFuture.<Optional<Users>>completedFuture(Optional.ofNullable(user_1))
                );
        //when

        ObjectMapper mapr = new ObjectMapper();

        String body = mapr.writeValueAsString(user_1);

        String expected = "";
        MvcResult response =
                this.mockMvc.perform(get("http://localhost:9090/api/v1/users/1").accept(MediaType.APPLICATION_JSON) )
                        .andExpect(request().asyncStarted())
                        .andExpect(status().is2xxSuccessful())
                        .andReturn();

        this.mockMvc.perform(asyncDispatch(response))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(body))
        ;
}
    
    @Test
    public void saveUserTest() throws Exception {
        //given
        given(usrSrv.save(Mockito.any()))
                .willReturn(
                        CompletableFuture.completedFuture(user_1)
                );
        //when

        ObjectMapper mapr = new ObjectMapper();

        String body = mapr.writeValueAsString(user_1);

        String expected = "";
        MvcResult response =
                this.mockMvc.perform(
                                post("http://localhost:9090/api/v1/users/")
                                        .accept(MediaType.APPLICATION_JSON)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(body)
                        )

                        .andExpect(request().asyncStarted())
                        .andExpect(status().is2xxSuccessful())
                        .andReturn();

        this.mockMvc.perform(asyncDispatch(response))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(body,false ))
        ;


    }
    
    @Test
    public void delUserTest() throws Exception {


        given(usrSrv.delete(Mockito.anyLong()))
                .willReturn(
                        CompletableFuture.completedFuture(true)
                );
        //when

        String expected = "";
        this.mockMvc.perform(delete("http://localhost:9090/api/v1/users/1") )
                .andDo(print())
                .andExpect(content().string(expected))
                .andExpect(status().is2xxSuccessful())
        ;
        //then

        }

}


