package com.dailynovel.web.controller.error;

//@Controller
//public class error implements ErrorController {
//	
//

	
//	@GetMapping("/error")
//		public String handleError(HttpServletRequest request) {
//		
//		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
//		
//		if(status !=null) {
//			int statusCode = Integer.valueOf(status.toString());
//			
//			
//			if(statusCode == HttpStatus.NOT_FOUND.value()) {
//				
//				return "error/404";
//			}
//			else {
//				return "/user/login";
//			}
//			
//		}
//		
//		
//		return "/user/login";
//	}
//}
