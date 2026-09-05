package com.proyecto_final.Pizza4You.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto_final.Pizza4You.dto.AuthResponse;
import com.proyecto_final.Pizza4You.dto.FullProfileRequest;
import com.proyecto_final.Pizza4You.dto.LoginRequest;
import com.proyecto_final.Pizza4You.dto.RegisterRequest;
import com.proyecto_final.Pizza4You.error.*;
import com.proyecto_final.Pizza4You.model.Cliente;
import com.proyecto_final.Pizza4You.model.Empleado;
import com.proyecto_final.Pizza4You.model.Rol;
import com.proyecto_final.Pizza4You.repositorio.ClienteRepository;
import com.proyecto_final.Pizza4You.repositorio.EmpleadoRepository;
import com.proyecto_final.Pizza4You.security.JwtService;



@Service
public class AuthService {
	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EmpleadoRepository empleadoRepository;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private PasswordEncoder passwordEncoder;
	private static final Rol CLIENTE_ROLE = createRole("CLIENTE");
	
	private static Rol createRole(String nombre) {
	    Rol rol = new Rol();
	    rol.setNombreCargo(nombre);
	    return rol;
	}
	
  
	// Controlo de manera diferente el login entre clientes y empleados
	public AuthResponse login(LoginRequest request) {
		Optional<Cliente> clienteOpt = clienteRepository.findByEmail(request.getEmail());

		if (clienteOpt.isPresent()) {
			Cliente cliente = clienteOpt.get();
			if (passwordEncoder.matches(request.getPassword(), cliente.getPass())) {
				
				String token = jwtService.generateToken(cliente.getEmail(), List.of(CLIENTE_ROLE));
				
				int idDireccion = -1;
				if (cliente.getDirecciones() != null && !cliente.getDirecciones().isEmpty()) {
					idDireccion = cliente.getDirecciones().get(0).getIdDir();
				}

				AuthResponse response = new AuthResponse(token, cliente.getIdCliente(), idDireccion);
				return response;
			}
		}

		Optional<Empleado> empleadoOpt = empleadoRepository.findByEmail(request.getEmail());

		if (empleadoOpt.isPresent()) {
			Empleado empleado = empleadoOpt.get();
			if (passwordEncoder.matches(request.getPassword(), empleado.getPass()) && empleado.getFechaBaja()==null) {

				List<Rol> rolesEmpleado = empleado.getRoles();

				String token = jwtService.generateToken(empleado.getEmail(), rolesEmpleado);

				AuthResponse response = new AuthResponse(token, empleado.getIdEmpleado(), -1);
				return response;
			}
		}
		
		throw new ErrorBadReq("Credenciales inválidas");

	}
	
	/**
	 * Registro de nuevo usuario
	 * 
	 * Verificación en caso de email en uso, el resto de verificaciones son realizadas en la propia app
	 * Encriptación de la contraseña mediante BCrypt
	 * 
	 * @param RegisterRequest 
	 * @return AuthResponse
	 */
	
	public AuthResponse register(RegisterRequest request) {

		if (clienteRepository.findByEmail(request.getEmail()).isPresent()
				|| empleadoRepository.findByEmail(request.getEmail()).isPresent()) {
			throw new ErrorBadReq("El email ya está en uso");
		}

		Cliente nuevoCliente = new Cliente();
		nuevoCliente.setEmail(request.getEmail());
		String passEncriptada = passwordEncoder.encode(request.getPassword());
		nuevoCliente.setPass(passEncriptada);

		clienteRepository.save(nuevoCliente);


		String token = jwtService.generateToken(nuevoCliente.getEmail(), null);

		AuthResponse response = new AuthResponse(token, nuevoCliente.getIdCliente(), -1);

		return response;
	}
	
	
	/**
	 * 
	 * Actualización transaccional de datos de un usuario según email
	 * 
	 * 
	 * 
	 * @param request
	 * @param emailUsuario
	 * @return Datos disponibles del usuario para su visualización
	 */
	@Transactional
	public FullProfileRequest update (FullProfileRequest request, String emailUsuario) {
		

        // Busco el cliente para verificar que existe
        Cliente cliente = clienteRepository.findByEmail(emailUsuario)
        		.orElseThrow(() -> new ErrorNotFound("Usuario no encontrado"));
        
     
           
            cliente.setNombre(request.getNombre());
            cliente.setTlf(request.getTelefono());
            
           
            clienteRepository.save(cliente);

            return new FullProfileRequest(cliente.getNombre(), cliente.getTlf());
        }
	
	

}
