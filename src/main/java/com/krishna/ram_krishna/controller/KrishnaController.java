package com.krishna.ram_krishna.controller;

import com.krishna.ram_krishna.dto.AddToCartRequest;
import com.krishna.ram_krishna.dto.CartItemDto;
import com.krishna.ram_krishna.dto.CartResponse;
import com.krishna.ram_krishna.dto.LoginRequest;
import com.krishna.ram_krishna.dto.LoginResponse;
import com.krishna.ram_krishna.dto.PriceCalculationResponse;
import com.krishna.ram_krishna.dto.QuizResultResponse;
import com.krishna.ram_krishna.dto.QuizSubmissionRequest;
import com.krishna.ram_krishna.dto.ProgramJoinRequest;
import com.krishna.ram_krishna.dto.ArchanaBookingRequest;
import com.krishna.ram_krishna.model.ArchanaBooking;
import com.krishna.ram_krishna.model.CartItem;
import com.krishna.ram_krishna.model.Product;
import com.krishna.ram_krishna.model.Program;
import com.krishna.ram_krishna.model.ProgramRegistration;
import com.krishna.ram_krishna.model.Quiz;
import com.krishna.ram_krishna.model.QuizAttempt;
import com.krishna.ram_krishna.model.User;
import com.krishna.ram_krishna.repository.SevaRequestRepository;
import com.krishna.ram_krishna.repository.CartItemRepository;
import com.krishna.ram_krishna.repository.ProductRepository;
import com.krishna.ram_krishna.repository.QuizAttemptRepository;
import com.krishna.ram_krishna.repository.QuizRepository;
import com.krishna.ram_krishna.repository.ProgramRegistrationRepository;
import com.krishna.ram_krishna.repository.ProgramRepository;
import com.krishna.ram_krishna.repository.ArchanaBookingRepository;
import com.krishna.ram_krishna.repository.UserRepository;
import com.krishna.ram_krishna.repository.PledgeRepository;
import com.krishna.ram_krishna.repository.PrayerRepository;
import com.krishna.ram_krishna.model.Pledge;
import com.krishna.ram_krishna.model.Prayer;
import com.krishna.ram_krishna.dto.PledgeSubmissionRequest;
import com.krishna.ram_krishna.dto.PrayerSubmissionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import com.krishna.ram_krishna.security.JwtUtil;
import com.krishna.ram_krishna.dto.LoginJwtResponse;
import org.springframework.beans.factory.annotation.Autowired;



import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class KrishnaController {

    private final UserRepository userRepository;
    private final ArchanaBookingRepository archanaBookingRepository;
    private final SevaRequestRepository sevaRequestRepository;
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;
    private final ProgramRepository ProgramRepository;
    private final ProgramRegistrationRepository ProgramRegistrationRepository;
    private final QuizRepository quizRepository;
    private final QuizAttemptRepository quizAttemptRepository;

    private final PledgeRepository pledgeRepository;
    private final PrayerRepository prayerRepository;

    @Autowired
    private JwtUtil jwtUtil;
    // Submit Pledge API
    @PostMapping("/pledge/submit")
    public ResponseEntity<String> submitPledge(@RequestBody PledgeSubmissionRequest request) {
        if (request.getUniqueId() == null || request.getUniqueId().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Login required to join Pledge");
        }
        Pledge pledge = new Pledge();
        pledge.setUniqueId(request.getUniqueId());
        pledge.setSacredCommitment(request.getSacredCommitment());
        pledge.setPersonalCommitment(request.getPersonalCommitment());
        pledgeRepository.save(pledge);
        return ResponseEntity.ok("Pledge submitted successfully");
    }

    // Submit Prayer API
    @PostMapping("/prayer/submit")
    public ResponseEntity<String> submitPrayer(@RequestBody PrayerSubmissionRequest request) {
        if (request.getUniqueId() == null || request.getUniqueId().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Login required to join Prayer");
        }
        Prayer prayer = new Prayer();
        prayer.setUniqueId(request.getUniqueId());
        prayer.setPrayer(request.getPrayer());
        prayerRepository.save(prayer);
        return ResponseEntity.ok("Prayer submitted successfully");
    }

    // Login API
    @PostMapping("/login")
    public ResponseEntity<LoginJwtResponse> login(@RequestBody LoginRequest request) {
        Optional<User> existingUser = userRepository.findByMobileNumber(request.getMobileNumber());
        User user;
        String message;
        if (existingUser.isPresent()) {
            user = existingUser.get();
            message = "Login successful";
        } else {
            User newUser = new User();
            newUser.setMobileNumber(request.getMobileNumber());
            newUser.setName(request.getName());
            newUser.setUniqueId(UUID.randomUUID().toString());
            newUser.setRewards(BigDecimal.ZERO);
            user = userRepository.save(newUser);
            message = "Registration successful";
        }
        String token = jwtUtil.generateToken(user.getUniqueId());
        return ResponseEntity.ok(new LoginJwtResponse(
            user.getUniqueId(),
            user.getName(),
            message,
            user.getRewards(),
            token
        ));
    }

    // Submit Service Booking
    @PostMapping("/booking/service")
    public ResponseEntity<String> submitArchanaBooking(@RequestBody ArchanaBookingRequest request) {
        User user = getUserOrCreateAnonymous(request.getUniqueId(), request.getName());
        
        ArchanaBooking booking = new ArchanaBooking();
        booking.setUserUniqueId(request.getUniqueId());
        booking.setUserId(user.getId());
        booking.setUserName(request.getName());
        booking.setPrayers(request.getPrayers());
        booking.setArchanaId(request.getServiceId());
        booking.setArchanaName(request.getServiceName());
        
        archanaBookingRepository.save(booking);
        return ResponseEntity.ok("Service booking submitted successfully");
    }

    // Submit Seva Request
    @PostMapping("/seva/request")
    public ResponseEntity<String> submitSevaRequest(@RequestBody com.krishna.ram_krishna.dto.SevaRequest request) {
        User user = getUserOrCreateAnonymous(request.getUniqueId(), request.getName());
        
        com.krishna.ram_krishna.model.SevaRequest sevaReq = new com.krishna.ram_krishna.model.SevaRequest();
        sevaReq.setUserUniqueId(request.getUniqueId());
        sevaReq.setUserId(user.getId());
        sevaReq.setUserName(request.getName());
        sevaReq.setDetails(request.getDetails());
        sevaReq.setSevaId(request.getSevaId());
        sevaReq.setSevaName(request.getSevaName());
        sevaReq.setMoney(request.getMoney());
        
        sevaRequestRepository.save(sevaReq);
        return ResponseEntity.ok("Seva request submitted successfully");
    }

    // Add Product to Cart (Requires login-like uniqueId)
    @PostMapping("/cart/add")
    public ResponseEntity<String> addToCart(@RequestBody AddToCartRequest request) {
        if (request.getUniqueId() == null || request.getUniqueId().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Login required to add items to cart");
        }
        
        Optional<Product> productOpt = productRepository.findById(request.getProductId());
        if (!productOpt.isPresent()) {
            return ResponseEntity.badRequest().body("Product not found");
        }
        
        Product product = productOpt.get();
        User user = getUserOrCreateAnonymous(request.getUniqueId(), null);
        
        // Check if item already exists in cart
        List<CartItem> existingItems = cartItemRepository.findByUserUniqueId(request.getUniqueId());
        Optional<CartItem> existingItem = existingItems.stream()
            .filter(item -> item.getProductId().equals(request.getProductId()))
            .findFirst();
            
        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + request.getQuantity());
            item.setTotalPrice(item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
            cartItemRepository.save(item);
        } else {
            CartItem cartItem = new CartItem();
            cartItem.setUserUniqueId(request.getUniqueId());
            cartItem.setUserId(user.getId());
            cartItem.setProductId(request.getProductId());
            cartItem.setProductName(product.getName());
            cartItem.setUnitPrice(product.getPrice());
            cartItem.setQuantity(request.getQuantity());
            cartItem.setTotalPrice(product.getPrice().multiply(BigDecimal.valueOf(request.getQuantity())));
            cartItemRepository.save(cartItem);
        }
        
        return ResponseEntity.ok("Item added to cart successfully");
    }

    // Get Cart and Calculate Price
    @GetMapping("/cart/{uniqueId}")
    public ResponseEntity<CartResponse> getCart(@PathVariable String uniqueId) {
        List<CartItem> cartItems = cartItemRepository.findByUserUniqueId(uniqueId);
        
        List<CartItemDto> itemDtos = cartItems.stream()
            .map(item -> new CartItemDto(
                item.getProductId(),
                item.getProductName(),
                item.getUnitPrice(),
                item.getQuantity(),
                item.getTotalPrice()
            ))
            .collect(Collectors.toList());
            
        BigDecimal totalPrice = cartItems.stream()
            .map(CartItem::getTotalPrice)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
            
        int totalItems = cartItems.stream()
            .mapToInt(CartItem::getQuantity)
            .sum();
        
        return ResponseEntity.ok(new CartResponse(itemDtos, totalPrice, totalItems));
    }

    // Join Program
    @PostMapping("/Program/join")
    public ResponseEntity<String> joinProgram(@RequestBody ProgramJoinRequest request) {
        if (request.getUniqueId() == null || request.getUniqueId().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Login required to join Program");
        }
        
        Optional<Program> ProgramOpt = ProgramRepository.findById(request.getProgramId());
        if (!ProgramOpt.isPresent()) {
            return ResponseEntity.badRequest().body("Program not found");
        }
        
        Program Program = ProgramOpt.get();
        
        // Check if already registered
        if (ProgramRegistrationRepository.existsByUserUniqueIdAndProgramId(request.getUniqueId(), request.getProgramId())) {
            return ResponseEntity.badRequest().body("Already registered for this Program");
        }
        
        // Check capacity
        if (Program.getEnrolled() >= Program.getCapacity()) {
            return ResponseEntity.badRequest().body("Program is full");
        }
        
        User user = getUserOrCreateAnonymous(request.getUniqueId(), request.getName());
        
        ProgramRegistration registration = new ProgramRegistration();
        registration.setUserUniqueId(request.getUniqueId());
        registration.setUserId(user.getId());
        registration.setUserName(request.getName());
        registration.setProgramId(request.getProgramId());
        registration.setProgramName(Program.getName());
        registration.setDetails(request.getDetails());
        
        ProgramRegistrationRepository.save(registration);
        
        // Update Program enrolled count
        Program.setEnrolled(Program.getEnrolled() + 1);
        ProgramRepository.save(Program);
        
        return ResponseEntity.ok("Successfully registered for Program");
    }

    // Take Quiz and Calculate Marks
    @PostMapping("/quiz/submit")
    @Transactional
    public ResponseEntity<QuizResultResponse> submitQuiz(@RequestBody QuizSubmissionRequest request) {
        Optional<Quiz> quizOpt = quizRepository.findById(request.getQuizId());
        if (!quizOpt.isPresent()) {
            return ResponseEntity.badRequest().body(null);
        }
        
        Quiz quiz = quizOpt.get();
        User user = getUserOrCreateAnonymous(request.getUniqueId(), null);
        
        // Calculate correct answers
        int correctCount = 0;
        List<String> correctAnswers = quiz.getCorrectAnswers();
        List<String> userAnswers = request.getAnswers();
        
        for (int i = 0; i < Math.min(correctAnswers.size(), userAnswers.size()); i++) {
            if (correctAnswers.get(i).equalsIgnoreCase(userAnswers.get(i))) {
                correctCount++;
            }
        }
        
        // Calculate reward
        BigDecimal rewardEarned = quiz.getRewardPerCorrect()
            .multiply(BigDecimal.valueOf(correctCount));
        
        // Update user rewards
        user.setRewards(user.getRewards().add(rewardEarned));
        userRepository.save(user);
        
        // Save quiz attempt
        QuizAttempt attempt = new QuizAttempt();
        attempt.setUserUniqueId(request.getUniqueId());
        attempt.setUserId(user.getId());
        attempt.setQuizId(request.getQuizId());
        attempt.setQuizTitle(quiz.getTitle());
        attempt.setUserAnswers(userAnswers);
        attempt.setCorrectCount(correctCount);
        attempt.setTotalQuestions(correctAnswers.size());
        attempt.setRewardEarned(rewardEarned);
        
        quizAttemptRepository.save(attempt);
        
        double percentage = (double) correctCount / correctAnswers.size() * 100;
        String message = percentage >= 70 ? "Great job!" : "Keep practicing!";
        
        return ResponseEntity.ok(new QuizResultResponse(
            correctCount, 
            correctAnswers.size(), 
            percentage, 
            rewardEarned, 
            message
        ));
    }

    // Calculate Total Price to Pay
    @GetMapping("/price/calculate/{uniqueId}")
    public ResponseEntity<PriceCalculationResponse> calculateTotalPrice(@PathVariable String uniqueId) {
        // Calculate cart total
        List<CartItem> cartItems = cartItemRepository.findByUserUniqueId(uniqueId);
        BigDecimal cartTotal = cartItems.stream()
            .map(CartItem::getTotalPrice)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        // Calculate seva money total
        List<com.krishna.ram_krishna.model.SevaRequest> activities = sevaRequestRepository.findByUserUniqueId(uniqueId);
        BigDecimal sevaTotal = activities.stream()
            .map(com.krishna.ram_krishna.model.SevaRequest::getMoney)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        BigDecimal grandTotal = cartTotal.add(sevaTotal);
        
        String message = grandTotal.compareTo(BigDecimal.ZERO) > 0 ? 
            "Total amount to be paid" : "No pending payments";
        
        return ResponseEntity.ok(new PriceCalculationResponse(
            cartTotal, 
            sevaTotal, 
            grandTotal, 
            message
        ));
    }

    // Helper method to get user or create anonymous
    private User getUserOrCreateAnonymous(String uniqueId, String name) {
        if (uniqueId == null || uniqueId.trim().isEmpty()) {
            uniqueId = UUID.randomUUID().toString();
        }
        
        Optional<User> userOpt = userRepository.findByUniqueId(uniqueId);
        if (userOpt.isPresent()) {
            return userOpt.get();
        }
        
        User newUser = new User();
        newUser.setUniqueId(uniqueId);
        newUser.setName(name != null ? name : "Anonymous");
        newUser.setRewards(BigDecimal.ZERO);
        return userRepository.save(newUser);
    }

    // Sample data endpoints for testing
    @PostMapping("/sample-data")
    public ResponseEntity<String> createSampleData() {
        // Create sample products
        if (productRepository.count() == 0) {
            productRepository.save(new Product(null, "Bhagavad Gita As It Is", "Complete edition with commentary by Srila Prabhupada", new BigDecimal("350.00"), 100));
            productRepository.save(new Product(null, "Sacred Tulsi Mala", "Hand-crafted prayer beads for japa meditation", new BigDecimal("45.00"), 50));
            productRepository.save(new Product(null, "Divine Prasadam Box", "Blessed food offering from the temple", new BigDecimal("12.50"), 200));
        }
        
        // Create sample Program
        if (ProgramRepository.count() == 0) {
            ProgramRepository.save(new Program(null, "Discover yourself", "Find out who are you", java.time.LocalDateTime.now().plusDays(7), 50, 0));
        }
        
        // Create sample quiz
        if (quizRepository.count() == 0) {
            Quiz quiz = new Quiz();
            quiz.setTitle("Basic Spiritual Knowledge");
            quiz.setDescription("Test your basic spiritual knowledge");
            quiz.setQuestions(List.of("What is meditation?", "What is prayer?"));
            quiz.setCorrectAnswers(List.of("A", "B"));
            quiz.setRewardPerCorrect(new BigDecimal("5.00"));
            quizRepository.save(quiz);
        }
        
        return ResponseEntity.ok("Sample data created successfully");
    }

}

