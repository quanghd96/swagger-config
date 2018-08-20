Config swagger
1. Hiển thị cấu trúc các response

    ```@PostMapping("insert")
    public ResponseEntity<UserLogin> insertUser(@RequestBody HashMap<String, String> body, HttpServletRequest request) {
        String token = request.getHeader(AUTHORIZATION);
        System.out.println(token);
        return new ResponseEntity<>(new UserLogin("quangdn", "quangdn@arrow-tech.vn", "snncklascnkslncaklsn"), HttpStatus.OK);
    }
    ```
    Cần thêm object trả về sau ResponseEntity

2. Nhập token vào header cho các request

    ```@Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.demo"))
                .paths(PathSelectors.any())
                .build()
                .enable(true)
                .securitySchemes(Lists.newArrayList(apiKey()));
    }

    private ApiKey apiKey() {
        return new ApiKey(AUTHORIZATION, AUTHORIZATION, "header");
    }
    ```
    Thêm config security

3. Yêu cầu login khi truy cập trang /swagger-ui.

    ```@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/swagger-ui.html**").authenticated()
                .anyRequest().permitAll()
                .and()
                .httpBasic()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance())
                .withUser("admin")
                .password("admin")
                .roles("USER");
    }
    ```
    Sử dụng spring security để authen in memory